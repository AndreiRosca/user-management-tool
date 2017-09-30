
$(document).ready(function () {
	$(".deleteUserButton").click(function (e) {
		const userId = $(e.target).data("user-id");
		$.ajax({
			method: 'DELETE',
			url: `/users/${userId}/delete`
		}).done(function (data, status) {
			if (status == "success") {
				$(`#userRow_${userId}`).remove();
			}
		});
	});
});
