$("#menu-appli-btn").mouseenter(function() {
	if ( ! $("#menu-appli").hasClass("change") ) {
		openMenuApp();
	} 
});

$("#menu-appli-btn, #menu-appli-content").click(function() {
	if ( $("#menu-appli").hasClass("change") ) {
		closeMenuApp();
	} else{
		openMenuApp();
	}
});

function openMenuApp(){
	$("#menu-appli-btn-label").html("");
	$("#menu-appli").addClass("change");
	//$("#menu-appli-btn").addClass("change");
	$("#menu-appli-content").show(200);
};
function closeMenuApp(){
	$("#menu-appli-btn-label").html("Menu");
	$("#menu-appli").removeClass("change");
	//$("#menu-appli-btn").removeClass("change");
	$("#menu-appli-content").hide(200);
};