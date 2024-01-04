function successfullyLogout(){
    alert("You have been successfully logged out");
}

// Toggle sidebar
$('#toggleSidebar').on('click', function() {
    $('#sidebar').toggleClass('active');
});