$(function() {
	var availableStudents = [
	    "Chloé",
	    "Maéva",
	    "Baradi",
	    "Camille"
	];
	var availableTutors = [
	    "Sellami",
	    "Gaaloul",
	];
	var availableGroups = [
	    "G1A",
	    "G1B",
	    "G1C",
	    "G1D"
	];
	var availableStudentsAndGroups = availableStudents;
	for (var i = 0; i < availableGroups.length; i++) {
		availableStudentsAndGroups.push(availableGroups[i]);
	}
	$("#student").autocomplete({
		source: availableStudents
	});
	$("#tutor").autocomplete({
		source: availableTutors
	});
	$("#group").autocomplete({
	     source: availableGroups
	});
	$("#studentOrGroup").autocomplete({
	     source: availableStudentsAndGroups
	});
});