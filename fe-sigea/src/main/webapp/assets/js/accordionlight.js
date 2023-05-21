$("body").delegate(".accordionlight", "click", function(){
	  
	var block = $(this).closest(".row");
	var setid = '';
	
	setid = block.find(".frecciafiltro").attr('id');
	
	 if(setid == 'frecciaopen'){
		 block.find(".blockaccordiumx").hide();		 
		 block.find(".active.in.blockaccordium").hide();
		 block.find(".frecciafiltro").prop('id', 'frecciaclose');
		 block.find(".frecciafiltro").attr("src", context+"/assets/images/FRECCIA_GIU.svg");
	 }else{
		 block.find(".blockaccordiumx").show();
		 block.find(".active.in.blockaccordium").show();
		 block.find(".frecciafiltro").prop('id', 'frecciaopen');
		 block.find(".frecciafiltro").attr("src", context+"/assets/images/FRECCIA_SU.svg");		 
	 }	
});
