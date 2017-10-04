$(document).ready(function() {
	setUpRemoveFrameworkButtonHandler();
	setUpAddFrameworkButtonHandler();
});

function setUpRemoveFrameworkButtonHandler() {
	$(".removeFrameworkButton").click(removeFrameworkButtonHandler);
}

function removeFrameworkButtonHandler(e) {
	const frameworkIndex = getFrameworkIndex(e);
	$(`#framework_${frameworkIndex}`).remove();	
}

function setUpAddFrameworkButtonHandler() {
	$(".addFrameworkButton").click(function(e) {
		const newFrameworkIndex = getNextFrameworkIndex();
		const newFrameworkElement = $(`
				<div class="col-sm-4 input-group"
				id="framework_${newFrameworkIndex}">
				<input class="form-control" type="text"
					name="frameworks[${newFrameworkIndex}]"
					required=""
					data-parsley-errors-container="#framework_${newFrameworkIndex}_Errors" /> <span
					class="input-group-btn">
					<button type="button"
						class="btn btn-default removeFrameworkButton"
						data-framework-index="${newFrameworkIndex}">
						<span class="glyphicon glyphicon-remove"></span> Remove
					</button>
				</span>
		</div>
		`);
		newFrameworkElement.click(removeFrameworkButtonHandler);
		$("#frameworksContainer").append(newFrameworkElement);
		$("#frameworksContainer").append(`<div id="framework_${newFrameworkIndex}_Errors"></div>`);
	});
}

function getNextFrameworkIndex() {
	let newFrameworkIndex = 0;
	$("#frameworksContainer div").each(function (index, element) {
		let currentFrameworkIndex = element.id.substring(element.id.indexOf('_') + 1);
		if (currentFrameworkIndex > newFrameworkIndex)
			newFrameworkIndex = currentFrameworkIndex;
	});
	return parseInt(newFrameworkIndex) + 1;
}

function getFrameworkIndex(e) {
	return $(e.target).data("framework-index");
}
