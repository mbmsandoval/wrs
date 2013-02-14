function handleChange(checklistNames, form) {
	var i, checklistName, containerOfTotalOptionsOnChecklist;
	for (i = 0; i < checklistNames.length; i++) {
		checklistName = checklistNames[i];
		document.getElementById(checklistName + '_search').value = "";
		document.getElementById(checklistName + '_searchtext_begin').checked = false;
		if (checklistName != "year") {
			containerOfTotalOptionsOnChecklist = document
					.getElementById(checklistName + '_size');
			if (null != containerOfTotalOptionsOnChecklist) {
				resetChecklistOptions(checklistName,
						containerOfTotalOptionsOnChecklist);
			}
		}
	}

	form.submit();
}

function resetChecklistOptions(checklistName,
		containerOfTotalOptionsOnChecklist) {
	var i, checkboxOnList;
	var totalOptionsOnChecklist = containerOfTotalOptionsOnChecklist.value;
	for (i = 0; i < totalOptionsOnChecklist; i++) {
		checkboxOnList = document.getElementById(checklistName + "_" + i);
		checkboxOnList.checked = false;
	}
}

function searchOnCheckList(checklistName) {
	var containerOfTotalOptionsOnChecklist = document
			.getElementById(checklistName + '_size');

	if (null != containerOfTotalOptionsOnChecklist) {
		var textToSearch = document.getElementById(checklistName + '_search').value;
		var startsWith = document.getElementById(checklistName
				+ '_searchtext_begin');
		var totalOptionsOnChecklist = containerOfTotalOptionsOnChecklist.value;

		textToSearch = escapeSpecialCharactersOnSearchString(textToSearch);
		var lengthOfTextToSearch = textToSearch.length;

		var regularExpression = RegExp(textToSearch, "gi");

		var idOfFirstItemFound = null;
		var i = -1;
		for (i = 0; i < totalOptionsOnChecklist; i++) {
			var idOfTargetLabel = 'label_on_' + checklistName + '_' + i;
			var targetLabel = document.getElementById(idOfTargetLabel);

			var stringToCompare = " ";

			if (startsWith.checked) {
				stringToCompare = targetLabel.innerText.substring(0,
						lengthOfTextToSearch);
			} else {
				stringToCompare = targetLabel.innerText;
			}

			var result = stringToCompare.search(regularExpression);
			if (result > -1) {
				targetLabel.style.backgroundColor = "#5a980b";
				targetLabel.style.color = "white";
				if (null == idOfFirstItemFound) {
					idOfFirstItemFound = idOfTargetLabel;
				}
			} else {
				targetLabel.style.backgroundColor = "white";
				targetLabel.style.color = "black";
			}
		}

		if ((-1 < i) && (null != idOfFirstItemFound)) {
			scroll(document.getElementById(idOfFirstItemFound), document
					.getElementById('form_' + checklistName));
		}

	}

	return;

}

function scroll(element, parent) {
	$(parent).animate(
			{
				scrollTop : $(parent).scrollTop() + $(element).offset().top
						- $(parent).offset().top
			}, {
				duration : 'slow',
				easing : 'swing'
			});
	$('html,body').animate({
		scrollTop : $(parent).offset().top - ($(window).height() / 3)
	}, {
		duration : 1000,
		easing : 'swing'
	});

}

function escapeSpecialCharactersOnSearchString(searchString) {
	var escapeExpression, escapedString;
	escapeExpression = /\(/i;
	escapedString = searchString.replace(escapeExpression, "\\(");
	searchString = escapedString;
	escapeExpression = /\)/i;
	escapedString = searchString.replace(escapeExpression, "\\)");
	searchString = escapedString;
	escapeExpression = /\./i;
	escapedString = searchString.replace(escapeExpression, "\\.");
	searchString = escapedString;
	escapeExpression = /\+/i;
	escapedString = searchString.replace(escapeExpression, "\\+");
	searchString = escapedString;
	escapeExpression = /\?/i;
	escapedString = searchString.replace(escapeExpression, "\\?");
	searchString = escapedString;
	return searchString;
}
