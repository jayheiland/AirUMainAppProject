if(!window.com) com = new Object();
if(!com.TI) com.TI = new Object();
com.TI.tokenPoller = function(options) {
	var WIDGET_NAMESPACE = 'TITokenPoller',
		INSTANCE_INTERVAL_ID;
				
	// define some defaults at this level so that we can overwrite after it's been extended
	var defaults = {
			"paramPage": "somepage.html", 
			"refreshRate": 10 * 1000,
			"valueMap": [{
				"paramId": "",
				"outputSuccess": function(output) {},
				"outputDefault": function() {}
			}]
		},
		options = $.extend(defaults, options),
		invervalRunning = false;

	function intervalFunc() {
		$.ajax({
			"type": "GET",
			"url": options.paramPage,
			"cache": false,
			"dataType": "html"
		}).done(function(data, status, xhr) {
			var i = 0,
				len = options.valueMap.length;
			if (status != 'success') {
				for (i=0;i<len;i++) {
					options.valueMap[i].outputDefault();
				}
			} else {
				var dataPage = $(data);
				for (i=0;i<len;i++) {
					options.valueMap[i].outputSuccess(dataPage.filter('#'+options.valueMap[i].paramId).text());
				}	
			}
			invervalRunning = false;
		});
	}

	INSTANCE_INTERVAL_ID = setInterval(function(){
		if (!invervalRunning) {
			invervalRunning = true;
			intervalFunc();
		}
	}, options.refreshRate);

	this.destroy = function() {
		invervalRunning = false;
		clearInterval(INSTANCE_INTERVAL_ID);
	};
};