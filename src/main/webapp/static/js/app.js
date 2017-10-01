$(document).ready(function() {
	setUpDeleteUserButtonHandler();
	setUpQueryUserButtonHandler();
	setUpUpdateUserButtonHandler();
});

function setUpDeleteUserButtonHandler() {
	$(".deleteUserButton").click(function(e) {
		const userId = getUserId(e);
		$.ajax({
			method : 'DELETE',
			url : `/users/${userId}/delete`
		}).done(function(data, status) {
			if (status == "success") {
				$(`#userRow_${userId}`).remove();
			}
		});
	});
}

function setUpQueryUserButtonHandler() {
	$(".showUserButton").click(function(e) {
		const userId = getUserId(e);
		location.href = `/users/${userId}`;
	});

}

function setUpUpdateUserButtonHandler() {
	$(".updateUserButton").click(function (e) {
		const userId = getUserId(e);
		location.href = `/users/${userId}/update`;
	});
}

function getUserId(e) {
	return $(e.target).data("user-id");
}
