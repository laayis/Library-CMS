var counter = 1;
var free = [true, true, true, true];

$(document).ready(function() {
	var limit = 5;
	
	$("#checkItem").click(function() {
		if (counter == limit)  {
	          alert("You have reached the limit of adding " + counter + " items.");
	     }
	     else {
	    	  var pos = getFreePosition();
	          var newdiv = document.createElement("newItem" + pos);
	          newdiv.innerHTML = "<div class=\"control-group\">" +
	          "<label class=\"control-label\" for=\"doc_id\">Document ID</label>" +
	          "<div class=\"controls\">" +
	          "<input type=\"text\" name=\"copies[" + pos + "].doc_id\" placeholder=\"Document ID\">"
	          +"</div>"
	          +"<label class=\"control-label\" for=\"copy_no\">Copy Number</label>" +
	          "<div class=\"controls\">" +
	          "<input type=\"text\" name=\"copies[" + pos + "].copy_no\" placeholder=\"Copy #\">" +
	          "</div>" +
	          "<div class=\"offset2 btn-group-wrap controls\">" +
	          "<div class=\"btn-group\">" +
	          "<button type=\"button\" class=\"btn btn-danger\" id=\"removeButton\"" +
	          "onClick=\"removeElement(" + pos + ")\">Remove Item" +
	          "</button>" +
	          "</div>" +
	          "</div>" +
	          "</div>";
	          document.getElementById("items").appendChild(newdiv);
	          counter++;
	     }

	});
	
	function getFreePosition(){
		var i = 0;
		while (!free[i] && i < free.length){
			i++;
		}
		
		if (i == free.length){
			// This should never happen.
			return 0;
		}
		else {
			free[i] = false;
			return i + 1;
		} 	
	};
	
});

function removeElement(pos){
	console.log(pos);
	console.log("newitem" + pos);
	var child = document.getElementsByTagName("newitem" + pos)[0];
	document.getElementById("items").removeChild(child);
	restoreFreePosition(pos);
	counter--;
};

function restoreFreePosition(pos){
	free[pos-1] = true;
};