var curr_month;
var curr_year;
var curr_day;
var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var num_of_days;
var NCmonth;
var NCyear;



function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function parseJSON(){
	var jsonstr = document.getElementById('taskjson').innerHTML;
	var resultsJSON = jQuery.parseJSON(jsonstr);
	var toDo = resultsJSON.TodoList;
	var tasksMap = new Map();
	for(var i = 0; i < toDo.length; i++){
		var taskTitle = toDo[i].TaskName;
		var taskDue = toDo[i].TaskDueDate;
		tasksMap.set(taskTitle, taskDue);
	}
	//go through and add tasks
	for(let [key, value] of tasksMap){
		addPastTask(key, value);
	}
}

function origin(){
    var date = new Date();
    document.getElementById('calheading').innerHTML = months[date.getMonth()] + ' ' + date.getFullYear();
    curr_month = date.getMonth();
    curr_year = date.getFullYear();
    NCmonth = date.getMonth();
    NCyear = date.getFullYear();
    curr_day = date.getDate();
    num_of_days = getNumOfDays();
    reloadCal();
    parseJSON();
}

function getNumOfDays() {
    const date = new Date();
    return new Date(curr_year, curr_month + 1, 0).getDate();
}

function addTask(name, dat) {
    //create li
    var curr_li = $("<li></li>");
    curr_li.addClass("event");
    curr_li.text(name);
    //push to ul
    var id_str = "#" + dat;
    $(id_str).append(curr_li);
    //sendData(id_str);
    window.location.href="calendarDispatcher?taskName="+name+"&taskDueDate="+dat;
}

function addPastTask(name, dat) {
	var curr_li = $("<li></li>");
    curr_li.addClass("event");
    curr_li.text(name);
    var id_str = "#" + dat;
    $(id_str).append(curr_li);
}

function reloadCal(){
    var tmp = 1;
    var body = $("<tbody></tbody>");
    body.attr('id', 'table_body');
    //first day calc
    var new_Date = new Date(months[curr_month] + " 1, " + curr_year);
    var newDay = new_Date.getDay();
    var start = newDay;
    //num of table rows
    for(let i = 0; i < 7; i++){
        //make tr
        //make table row
        var week = $("<tr></tr>");
        //for the first row add blank td's
        if(i == 0){
            //add blank days (filler)
            for(let k = 0; k < start; k++){
                var day = $("<td></td>");
                var div1 = $("<div></div>");
                var div2 = $("<div></div>");
                div1.addClass("blank-day-field-wrapper");
                div2.addClass("blank-events-wrapper");
                var spn1 = $("<span></span>");
                var spn2 = $("<span></span>");
                spn1.addClass("blank-day-field");
                spn2.addClass("blank-event");
                div1.append(spn1);
                div2.append(spn2);
                day.append(div1);
                day.append(div2);
                week.append(day);
            }
            //add the start of the real days 
            //this handels that the date is in under the correct day of the week
            for(let j = start; j < 7; j++){
                if(tmp <= num_of_days){
                    //make td
                    var tmpday = tmp;
                    var tmpmnth = (curr_month+1);
                    if(tmpday <= 9){
                        tmpday = "0" + tmpday;
                    }
                    if(tmpmnth <= 9){
                        tmpmnth = "0" + tmpmnth;
                    }
                    var datestr = curr_year  + "-" + tmpmnth + "-" + tmpday;
                    var day = $("<td></td>");
                    var div1 = $("<div></div>");
                    var div2 = $("<div></div>");
                    div1.addClass("day-field-wrapper");
                    div2.addClass("events-wrapper");
                    var spn1 = $("<span></span>");                    
                    //var spn2 = $("<span></span>"); 
                    var ul1 = $("<ul></ul>");
                    spn1.addClass("day-field");
                    //spn2.addClass("event");
                    //spn2.attr("id",datestr);
                    ul1.attr("id", datestr);
                    if(curr_month == NCmonth && curr_year == NCyear){
                        if(tmp == curr_day){
                            spn1.addClass("today");
                        }
                    }
                    spn1.text(tmp.toString());
                    div1.append(spn1);
                    //div2.append(spn2); //remove later
                    div2.append(ul1);
                    day.append(div1);
                    day.append(div2);
                    week.append(day);
                    tmp += 1;
                }
            }
        }
        //make the rest of the calendar
        else {
            for(let j = 0; j < 7; j++){
                if(tmp <= num_of_days){
                    //make td
                    var tmpday = tmp;
                    var tmpmnth = (curr_month+1);
                    if(tmpday <= 9){
                        tmpday = "0" + tmpday;
                    }
                    if(tmpmnth <= 9){
                        tmpmnth = "0" + tmpmnth;
                    }
                    var datestr = curr_year  + "-" + tmpmnth + "-" + tmpday;
                    var day = $("<td></td>");
                    var div1 = $("<div></div>");
                    var div2 = $("<div></div>");
                    div1.addClass("day-field-wrapper");
                    div2.addClass("events-wrapper");
                    var spn1 = $("<span></span>");
                    //var spn2 = $("<span></span>"); //remove later
                    var ul1 = $("<ul></ul>");
                    spn1.addClass("day-field");
                    //spn2.addClass("event"); //remove later
                    //spn2.attr("id",datestr); //remove later
                    ul1.attr("id", datestr);
                    if(curr_month == NCmonth && curr_year == NCyear){
                        if(tmp == curr_day){
                            spn1.addClass("today");
                        }
                    }
                    spn1.text(tmp.toString());
                    div1.append(spn1);
                    //div2.append(spn2); //remove later
                    div2.append(ul1);
                    day.append(div1);
                    day.append(div2);
                    week.append(day);
                    tmp += 1;
                }
            }
        }
        body.append(week);
        
    }
    
    $('#month_cal').append(body);
}

//chevron right
$("#chevright").click(function () {
    if(curr_month == 11){
        curr_month = 0;
        curr_year += 1;
    }
    else {curr_month += 1;}
    num_of_days = getNumOfDays();
    document.getElementById('calheading').innerHTML = months[curr_month] + ' ' + curr_year;
    $('#table_body').remove();
    reloadCal();
    parseJSON();
});

//chevron left
$("#chevleft").click(function () {
    if(curr_month == 0){
        curr_month = 11;
        curr_year -= 1;
    }
    else{curr_month -= 1;}
    num_of_days = getNumOfDays();
    document.getElementById('calheading').innerHTML = months[curr_month] + ' ' + curr_year;
    $('#table_body').remove();
    reloadCal();
    parseJSON();
});

var taskModal = document.getElementById('Modalform');
$('#addTask').click(function (){
    var modalBodyInput1 = taskModal.querySelector('.modal-body #task-title');
    var modalBodyInput2 = taskModal.querySelector('.modal-body #task-date');
    var task = modalBodyInput1.value;
    var datee = modalBodyInput2.value;
    addTask(task, datee);
})

window.onload = origin;
