/*!
 * DTOserializer.js 1.0.5
 * MIT license: https://opensource.org/licenses/MIT
 */

function bindingAddButton(addbutton){
	
    $("[data-addbutton='" + addbutton + "']").on("click", function(event, triggered){
    	var feeder = $(this).attr("data-feeder");
    	var isFeeded = false;
		if(!triggered && feeder != null && feeder != "" && feeder.length > 0){
			isFeeded = true;
			var feederValue = $('[name="'+ feeder +'"]').val();
			if(feederValue == null || feederValue == "" || feederValue.length == 0){
				return;
			}
		}
    	var ph = $(this).attr("data-ph");
    	var counter = parseInt($(this).attr("data-counter"), 10) + 1;
    	$(this).attr("data-counter", counter);
    	var dataTemplate = addbutton + ph;
    	var dataDynamic = addbutton;
    	var templateClone = $("[data-template='" + dataTemplate + "']").clone();
    	$(templateClone).find("[name*='"+ ph +"']").each(function(){
    		var name = $(this).attr("name");
    		name=name.replace(new RegExp(ph, 'g'), "["+counter+"]");
    		$(this).attr("name",name);
    	});
    	$(templateClone).find("[data-template*='"+ ph +"']").each(function(){
    		var innerDataTemplate = $(this).attr("data-template");
    		innerDataTemplate=innerDataTemplate.replace(new RegExp(ph, 'g'), "["+counter+"]");
    		$(this).attr("data-template",innerDataTemplate);
    	});
    	$(templateClone).find("[data-dynamic*='"+ ph +"']").each(function(){
    		var innerDataDynamic = $(this).attr("data-dynamic");
    		innerDataDynamic=innerDataDynamic.replace(new RegExp(ph, 'g'), "["+counter+"]");
    		$(this).attr("data-dynamic",innerDataDynamic);
    	});
    	$(templateClone).find("[data-addbutton*='"+ ph +"']").each(function(){
    		var innerDataAddbutton = $(this).attr("data-addbutton");
    		innerDataAddbutton=innerDataAddbutton.replace(new RegExp(ph, 'g'), "["+counter+"]");
    		$(this).attr("data-addbutton",innerDataAddbutton);
    	});
    	$(templateClone).find("[data-deletebutton*='"+ ph +"']").each(function(){
    		var innerDataDeletebutton = $(this).attr("data-deletebutton");
    		innerDataDeletebutton=innerDataDeletebutton.replace(new RegExp(ph, 'g'), "["+counter+"]");
    		$(this).attr("data-deletebutton",innerDataDeletebutton);
    	});
    	$(templateClone).find("[data-name*='"+ ph +"']").each(function(){
    		var innerDataName = $(this).attr("data-name");
    		innerDataName=innerDataName.replace(new RegExp(ph, 'g'), "["+counter+"]");
    		$(this).attr("data-name",innerDataName);
    	});
    	dataTemplate=dataTemplate.replace(new RegExp(ph, 'g'), "["+counter+"]");
		$(templateClone).attr("data-template",dataTemplate);
		$(templateClone).removeAttr( "hidden" );
		
		
		 flastorderaltro();
		 
		// console.log($(templateClone).html());
		
		$(templateClone).find(".order").val(lastorderaltro);
		//$(templateClone).find("input[name$='.ordineNumerico']").val(lastorderaltro);
		
		// to do 
		// var conta = $("[data-template^='"+dataDynamic+"']").length;
		
		// console.log(conta+"-----"+dataDynamic);
		
		// if(conta >2){
			$("[data-template='" + dataDynamic+ "_PH_']").after(templateClone);
		// }else{			
		//	templateClone.appendTo($("[data-dynamic='" + dataDynamic+ "']"));
		// }
		
		$(templateClone).find("[data-initialize*='"+ ph +"']").each(function(){
			var funcString = $(this).attr("data-initialize");
			funcString=funcString.replace(new RegExp(ph, 'g'), "");
			var inputName = $(this).attr("name");
			window[funcString](inputName);
		});
		var elementToTrigger = []
		$(templateClone).find("[data-dynabindingonload*='"+ ph +"']").each(function(){
			var funcString = $(this).attr("data-dynabindingonload");
			funcString=funcString.replace(new RegExp(ph, 'g'), "");
			var evento = $(this).attr("data-event");
			var nome = $(this).attr("name");
			if (!nome){
				nome = $(this).attr("data-name");
			}
			window[funcString](nome,evento);
			elementToTrigger.push(this);
		});
		if(!triggered){
			$.each(elementToTrigger, function() {
				$(this).trigger('change');
			});
		}
		$(templateClone).find("[data-feeder*='"+ ph +"']").each(function(){
			var innerDataFeeder = $(this).attr("data-feeder");
			innerDataFeeder=innerDataFeeder.replace(new RegExp(ph, 'g'), "["+counter+"]");
    		$(this).attr("data-feeder",innerDataFeeder);
		});
		$(templateClone).find("[data-feed*='"+ ph +"']").each(function(){
			var innerDataFeed = $(this).attr("data-feed");
			innerDataFeed=innerDataFeed.replace(new RegExp(ph, 'g'), "");
    		$(this).attr("data-feed",innerDataFeed);
		});
		if(isFeeded){
			var funcString = $('[name="'+ feeder +'"]').attr("data-feed");
			window[funcString](feeder,counter);
		}
    	var deletebutton = addbutton + "["+counter+"]";
    	bindingRemoveButton(deletebutton);
    	var innerAddButtons = $("[data-addbutton^='" + addbutton + "["+counter+"]" + "']");
    	innerAddButtons.each(function(){
    		bindingAddButton($(this).attr('data-addbutton'));
    	});
        var lista = listalingue();
    });    
}

function bindingRemoveButton(deletebutton){
	$("[data-deletebutton='" + deletebutton  + "']").on("click", function(event){
		var addbutton = deletebutton.substr(0, deletebutton.lastIndexOf("["));
		var counter = parseInt($("[data-addbutton='" + addbutton + "']").attr("data-counter"), 10);		
		$("[data-template='" + deletebutton + "']").remove();
		$("[data-addbutton='" + addbutton + "']").attr("data-counter", counter);
		listalingue();
	});	
}

(function($) {
	$.fn.serializeFormJSON = function() {
		var o = {};
		var a = this.serializeArray();
		$('#'+this.attr('id')).find('input[type=checkbox]:not(:checked)').each(function() {
	        a.push({name: this.name, value: 'false' });
	    });
		$('#'+this.attr('id')).find('input[type=radio]:not(:checked)').each(function() {
			var found = false;
			for(var i = 0; i < a.length; i++) {
			    if (a[i].name == this.name) {
			        found = true;
			        break;
			    }
			}			
			if (!found){
				a.push({name: this.name, value: "" });
			}
	    });
		$('#'+this.attr('id')).find('select').each(function() {
			var found = false;
			for(var i = 0; i < a.length; i++) {
			    if (a[i].name == this.name) {
			        found = true;
			        break;
			    }
			}			
			if (!found){
				a.push({name: this.name, value: null });
			}
	    });
		var tempA = [];
		var phCollection = [];
		$('#'+this.attr('id')).find('[data-ph]').each(function(){
			var ph = $(this).attr("data-ph");
			if (phCollection.indexOf(ph) === -1) {
				phCollection.push(ph);
			}
		});
		for(var i = 0; i < a.length; i++) {
			var found = false;
			for(var j = 0 ; j < phCollection.length; j++){
			    if (a[i].name.indexOf(phCollection[j]) > -1) {
			    	found = true;
			        break;
			    }
			}
			if (!found){
				tempA.push(a[i]);
			}
		}
		a = tempA;
		tempA = [];
		for(var i = 0; i < a.length; i++){
			var ignore = $('[name="'+ a[i].name +'"]').is('[data-ignorefield]');
			if(!ignore){
				tempA.push(a[i]);
			}
		}
		a = tempA;
		$.each(a, function() {
			var name;
			var isList = false;
			var isDate = false;
			if (this.name.endsWith("]")) {
				name = this.name.substring(0, this.name.lastIndexOf("["));
				isList = true;
			} else {
				if (/^(0?[1-9]|[12][0-9]|3[01])[\/](0?[1-9]|1[012])[\/]\d{4}$/.test(this.value)) {
					isDate = true;
				}
				name = this.name;
			}
			if (o[name]) {
				if (!o[name].push) {
					o[name] = [ o[this.name] ];
				}
				o[name].push(this.value || '');
			} else {
				if (isList) {
					o[name] = [];
					if(this.value!=null){
						o[name].push(this.value || '');
					}
				} else {
					if (isDate){
						o[name] = convertDataItaToEng(this.value) || '';
					}else{
						o[name] = this.value || '';
					}
				}
			}
		});
		var totalDeep =  detectTotalDeep(o);
		var i;
		var jsonObj = o;
		for (i = totalDeep; i > 0; i--) {
			jsonObj = buildObj2(jsonObj, i);
		}
		
		return handleCollection(jsonObj);
	};

	$.fn.serializeComplexDataForm = function() {
		var jsonData = this.serializeFormJSON();
		var formData = new FormData();
		formData.append('payload', JSON.stringify(jsonData));
		var filesInputSelector = this.find("input[type='file']");
		filesInputSelector.each(function() {
			if (this.files[0] != undefined) {
				formData.append($(this).attr('id'), this.files[0]);
			}
		});
		return formData;
	}
	
	$.fn.initializeBindingOnForm = function(){
		$('#'+this.attr('id')).find("[data-bindingonload]").each(function(){
			if ($(this).attr("data-bindingonload") == null || $(this).attr("data-bindingonload").length <= 0){
				var innerDataAddbutton = $(this).attr("data-addbutton");
				bindingAddButton(innerDataAddbutton);
			}else{
				var funcString = $(this).attr("data-bindingonload");
				var evento = $(this).attr("data-event");
				var nome = $(this).attr("name");
				window[funcString](nome,evento);
			}
		});
	}
	
	$.fn.deserializeJSONForm = function(json, selectizeJSActive){
		var flatJson = flattenObject(json);
		
		Object.keys(flatJson).forEach(function(key) {
			var multiKey = null;
			if(key.endsWith("]")){
				multiKey = key.substr(0, key.lastIndexOf("[")) + "[]";
			}
			if(!($("[name='"+key+"']").length) && !($("[name='"+multiKey+"']").length)){
				var addbutton;
				
				if (key.indexOf(".") < 0 && key.indexOf("[") > -1){
					addbutton = key.substr(0, key.lastIndexOf("["));
				}else{
					addbutton = key.substr(0, key.lastIndexOf("."));
					addbutton = addbutton.substr(0, addbutton.lastIndexOf("["));
				}
				$("[data-addbutton='" + addbutton + "']").trigger('click', true);
			}
		});
		
		var fixedFlatJson = {};
		
	    Object.keys(flatJson).forEach(function(fkey) {
	    	if(fkey.endsWith("]")){
		    	var multiKey = fkey.substr(0, fkey.lastIndexOf("[")) + "[]";
		    	if($("[name='"+multiKey+"']").length){
					if(!(multiKey in fixedFlatJson)){
						fixedFlatJson[multiKey] = [];
					}
					fixedFlatJson[multiKey].push(flatJson[fkey]);
				}else{
					fixedFlatJson[fkey] = flatJson[fkey];
				}
	    	}else if(/^\d{4}[\-](0?[1-9]|1[012])[\-](0?[1-9]|[12][0-9]|3[01])$/.test(flatJson[fkey])){
	    		fixedFlatJson[fkey] = convertDataEngToIta(flatJson[fkey]);
	    	}else{
				fixedFlatJson[fkey] = flatJson[fkey];
			}
	    });
		
		populateForm('#'+this.attr('id'),fixedFlatJson, selectizeJSActive);
		
	}
	
	function populateForm(frm, data, selectizeJSActive) {   
		$.each(data, function(key, value){  
		    var $ctrl = $('[name="'+key+'"]', frm);
		    if($ctrl.is('select')){
		        if (selectizeJSActive){
		        	var $select = $ctrl.selectize();
		        	var selectize = $select[0].selectize;
		        	
		        	selectize.setValue(value, false);
		        }else{
			        $("option",$ctrl).each(function(){
			        	if(Array.isArray(value) && value.length > 0){
			        		$('key').val(value);
			        	}else{
				            if (this.value==value) { 
				            	this.selected=true; 
				            }
			        	}
			        });
		        }
		    }else if($ctrl.is('textarea')){
		    	$ctrl.val(value);
		    }else {
		        switch($ctrl.attr("type"))  
		        {  
		            case "text": case "number": case "hidden": case "time": case "search":
		            	if ($ctrl.hasClass("wktime")){
		            		if (value == null || value == ''){
		            			$ctrl.wickedpicker({twentyFour:true, now : '00:00', minutesInterval: 5});
		            			$ctrl.val("HH : MM");
		            		}else{
		            			$ctrl.wickedpicker({twentyFour:true, now : value, minutesInterval: 5});
		            		}
		            	}else if($ctrl.hasClass("data")){
		            		var dateParts = value.split('/');
		            		var dateValue = new Date(dateParts[2],dateParts[1] - 1,dateParts[0]);
		            		var startDate = $ctrl.datepicker("getStartDate");		            		
		            		var dataoggiit = moment(dataserver, 'YYYY-MM-DD').format('DD/MM/YYYY');
		            		
		            		//var endDate = $ctrl.datepicker("getEndDate");
		            		$ctrl.datepicker('setStartDate', null);
		            		//$ctrl.datepicker('setEndDate', null);
		            		$ctrl.datepicker("setDate", dateValue );		            			            		            		
		            		
		            		 if(validator == true){
		            				if (dateValue<startDate){	 
				            			$ctrl.datepicker('setStartDate', dateValue);			            			
				            		}else{
					            		$ctrl.datepicker('setStartDate', startDate);			            	
				            		}
		    				 }else{
		    					 $ctrl.datepicker('setStartDate', dataoggiit);
		    				 }		            		
		            		
		            		$ctrl.datepicker('autoclose', true);
		            		//$ctrl.datepicker('setEndDate', endDate);
		            	}else{
		            		var getclass = $ctrl.attr("class");
		            		if(getclass == 'inputtotext totext'){
		            			$ctrl.val(value);
		            			$ctrl.after(value);
		            		}else{
		            			$ctrl.val(value);
		            		}
			         			            	
		            	}
		                break;   
		            case "checkbox":   
		                $ctrl.each(function(){
		                	if (value==true){
		                		$(this).prop('checked', true);
		                	}else{
		                		$(this).prop('checked', false);
		                	}
		                }); 
		                break;
		            case "radio":
		                $ctrl.each(function(){
		                	if (value.toString()==this.value){
		                		$(this).prop('checked', true);
		                	}else{
		                		$(this).prop('checked', false);
		                	}
		                }); 
		                break;
		        } 
		    } 
		    
		    
		    $ctrl.each(function(){
		    	if ($(this).attr("data-bindingonload")!= null && $(this).attr("data-bindingonload").length > 0 && $(this).attr("data-event") == 'change'){
					$(this).trigger('change');
			    }
		    });
		    
		    $ctrl.each(function(){
		    	if ($(this).attr("data-dynabindingonload")!= null && $(this).attr("data-dynabindingonload").length > 0 && $(this).attr("data-event") == 'change'){
		    		$(this).trigger('change');
			    }
		    });

		});  
		
	   	
	};
	
	function convertDataItaToEng(data){
		var dateParts = data.split('/');
		if (dateParts[2] != undefined){
			return dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
		}else{
			return '';
		}
	}
	
	function convertDataEngToIta(data){
		if (data !=null && data !=''){
			var dateParts = data.split('-');
			if (dateParts[2] != undefined){
				return dateParts[2] + '/' + dateParts[1] + '/' + dateParts[0];
			}else{
				return '';
			}
		}
	}
	
	function occurrences(string, subString, allowOverlapping) {
	    string += "";
	    subString += "";
	    if (subString.length <= 0) return (string.length + 1);

	    var n = 0,
	        pos = 0,
	        step = allowOverlapping ? 1 : subString.length;

	    while (true) {
	        pos = string.indexOf(subString, pos);
	        if (pos >= 0) {
	            ++n;
	            pos += step;
	        } else break;
	    }
	    return n;
	}
	
	function detectTotalDeep(o){
		var deep = 0;
		Object.keys(o).forEach(function(key) {
			var occurrence = occurrences(key, '.', true);
			if (occurrence>deep){
				deep = occurrence;
			}
		});
		return deep;
	}
	
	function buildObj2(o, deep){
		var obj = {}
		Object.keys(o).forEach(function(key) {
			if (occurrences(key, '.', true)==deep){
				var subKeyName = key.substring(key.lastIndexOf(".") + 1);
				var parentKeyName = key.substring(0,key.lastIndexOf("."));
				if(!(parentKeyName in obj)){
					obj[parentKeyName] = {};
				}
				var isList = false;
				var isDate = false;
				if (obj[parentKeyName][subKeyName]) {
					if (!obj[parentKeyName][subKeyName].push) {
						obj[parentKeyName][subKeyName] = [obj[parentKeyName][subKeyName]];
					}
					obj[parentKeyName][subKeyName].push(o[key]);
				} else {
					if (isList) {
						obj[parentKeyName][subKeyName] = [];
						obj[parentKeyName][subKeyName].push(o[key]);
					} else {
						obj[parentKeyName][subKeyName] = o[key];
					}
				}
			}else{
				obj[key] = o[key];
			}
		});
		return handleCollection(obj);
	}
	
	function handleCollection(obj){
		var obj2 = {}
		Object.keys(obj).forEach(function(key) {
			var subKeyName; 
			var parentKeyName;
			if (key.lastIndexOf(".")>0){
				subKeyName = key.substring(key.lastIndexOf(".") + 1);
				parentKeyName = key.substring(0,key.lastIndexOf(".")) + ".";
			}else{
				subKeyName = key.substring(key);
				parentKeyName = "";
			}
			if(subKeyName.endsWith("]")){
				var name = subKeyName.substring(0,subKeyName.indexOf("["));
				var newKeyName = parentKeyName + name;
				if(!(newKeyName in obj2)){
					obj2[newKeyName] = [];
				}
				obj2[newKeyName].push(obj[key]);
			}else{
				obj2[key] = obj[key];
			}
		});
		return obj2;
	}
	
	function flattenObject(graph) {
	    let result = {},
	        item,
	        key;

	    function recurr(graph, path) {
	        if (Array.isArray(graph)) {
	            graph.forEach(function (itm, idx) {
	                key = path + '[' + idx + ']';
	                if (itm && typeof itm === 'object') {
	                    recurr(itm, key);
	                } else {
	                    result[key] = itm;
	                }
	            });
	        } else {
	        	//commentata perché incompatibile con IE11
	            //Reflect.ownKeys(graph).forEach(function (p) {
	        	Object.keys(graph).forEach(function (p) {
	            	if (path.length > 0){
	                    key = path + '.' + p;
	            	}else{
	            		key = p;
	            	}
	                item = graph[p];
	                if (item && typeof item === 'object') {
	                    recurr(item, key);
	                } else {
	                    result[key] = item;
	                }
	            });
	        }
	    }
	    recurr(graph, '');
	    	    
	    var listFixedResult={};
	        
	    Object.keys(result).forEach(function(fkey) {
	    	if(Array.isArray(result[fkey]) && result[fkey].length > 0){
	    		for(var i = 0 ; i< result[fkey].length; i++){
	    			var newKey = fkey.substr(0,fkey.lastIndexOf("["));
	    			newKey = newKey + "[" + i + "]";
	    			listFixedResult[newKey] = result[fkey][i];
	    		}
	    	}else{
	    		listFixedResult[fkey] = result[fkey];
	    	}
	    });
	    
	    
	    return listFixedResult;
	}
})(jQuery);