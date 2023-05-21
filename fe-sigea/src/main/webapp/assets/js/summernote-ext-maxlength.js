(function (factory) {
    /* global define */
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    } else if (typeof module === 'object' && module.exports) {
        // Node/CommonJS
        module.exports = factory(require('jquery'));
    } else {
        // Browser globals
        factory(window.jQuery);
    }
}(function ($) {
    // Extends plugins for print plugin.
    $.extend($.summernote.plugins, {
        /**
         * @param {Object} context - context object has status of editor.
         */
        maxlength: function (context) {
            var self = this;
            var lengthhtml = 0;
            var layoutInfo = context.layoutInfo;
            var $editor = layoutInfo.editor;
            var $editable = layoutInfo.editable;            
            var $statusbar = layoutInfo.statusbar;
            var maxlength = $editor.parent().find('textarea').attr('data-maxlength');
            var id = $editor.parent().find('textarea').attr('id');
            var maxlengthtml = $editor.parent().find('textarea').attr('data-maxlengthtml');
            	
            if (maxlength) {
                self.$label = null;
                self.$label2 = null;

                self.initialize = function () {
	                    // var label = ui.button({contents:"hi"});
	                    var label = document.createElement("span");
	                    label.setAttribute("id", "nohtml");	
	                    self.$label = $(label);
	                    self.$label.addClass('bootstrap-maxlength badge badge-success');
	                    self.$label.css({position: 'absolute', left:'30%', transform:'translateX(-30%)'});
	                    $statusbar.append(self.$label);
	                    
	                    var label2 = document.createElement("span");
	                    label2.setAttribute("id", "withhtml");	
	                    self.$label2 = $(label2);
	                    self.$label2.addClass('bootstrap-maxlength badge badge-success');
	                    self.$label2.css({position: 'absolute', left:'50%', transform:'translateX(-50%)'});
	                    $statusbar.append(self.$label2);
	                    
	                    
	                    
	                    var convert = ConvertCharacter($editable.html());	                    
	                    var contatesto = convert.length; 
	                    
	                    if($editable.html() == '<p><br></p>' || $editable.text() == '<br>'){
	                    	contatesto = 0;
	                    	$editable.html('');
	                    }
	                
	                self.toggle(contatesto);
	                $editable.on('keyup', function(){ 
                    	
                    	 if($(this).html() == '<p><br></p>' || $(this).html() == '<br>'){
                            $(this).html('');
                         }
                    	 
                         var body2 =  $(this).html();
                         body2 = ConvertCharacter(body2);
                         var temp2 = document.createElement("div");
                         temp2.innerHTML = body2;
                         var sanitized2 = temp2.textContent || temp2.innerText;
                         
                         sanitized2 = ConvertCharacter(sanitized2);
                         var length = sanitized2.length;                        
                    	
                    	lengthhtml = body2.length;                       	
                        self.toggle(length,lengthhtml);
                    });
                    
                    $editable.on('change, focusout', function(){ 
                    	
                    	 if($(this).html() == '<p><br></p>' || $(this).html() == '<br>'){
                         	$(this).html('');
                    	 }
                    	 
                         var body3 =  $(this).html();
                         body3 = ConvertCharacter(body3);
                         var temp3 = document.createElement("div");
                         temp3.innerHTML = body3;
                         var sanitized3 = temp3.textContent || temp3.innerText;
                         sanitized3 = ConvertCharacter(sanitized3);
                         var length = sanitized3.length;                         
                    	
                        lengthhtml = body3.length;             
                        self.toggle(length,lengthhtml);
                    });
                };

                self.toggle = function(length,lengthhtml){
                	
                	//console.log(length+"---"+lengthhtml);
                	
                    self.$label.text("Caratteri testuali: "+length);
                    if(length > maxlength){
                        self.$label.addClass('badge-danger');
                        self.$label.removeClass('badge-success');
                    }else{
                        self.$label.addClass('badge-success');
                        self.$label.removeClass('badge-danger');
                    }
                    self.$label2.text("Caratteri complessivi: "+lengthhtml +" / "+ maxlengthtml);
                    if(lengthhtml > maxlengthtml){
                        self.$label2.addClass('badge-danger');
                        self.$label2.removeClass('badge-success');
                    }else{
                        self.$label2.addClass('badge-success');
                        self.$label2.removeClass('badge-danger');
                    }
                };
                
                
                
                
            }
        }
    });
}));


$( "textarea" ).keyup();

