$(document).ready(function(){
	// makeUrl();
	getResults();
});

var response = null;

function formSubmit(){
  var hotel=document.getElementById("form1").value;
  var location=document.getElementById("form2").value;
  var url = "RestaurantReview.html?hotel="+hotel+"&location="+location;
  window.location.href=url;
}

function myMap() {
	$.getJSON("data/restaurant.json",function(json) {
		var queryString = decodeURIComponent(window.location.search);
		queryString = queryString.substring(1);
		list=queryString.split("&");
		hotel=list[0].split("=")[1];
		var yelp_id;
		var opendata_id;
		$.each( json.restaurants, function(i, item ) {
			if(item.name==hotel){
				yelp_id="yelpId="+item.yelp_id;
				opendata_id="opendataId="+item.opendata_id;
			}
  	});
		var query="/search?"+yelp_id+"&"+opendata_id+"&"+list[1];
		// console.log(query);
		$.ajax({
	    url: query,
	    type: 'GET',
			contentType: 'application/json; charset=utf-8',
	    success: function(resultData) {
				var myCenter = new google.maps.LatLng(resultData.coordinates.latitude,resultData.coordinates.longitude);
			  var mapCanvas = $('#map')[0];
			  var mapOptions = {center: myCenter, zoom: 15};
			  var map = new google.maps.Map(mapCanvas, mapOptions);
			  var marker = new google.maps.Marker({position:myCenter});
			  marker.setMap(map);
	    }
	  });
	});
}

function makeUrl() {
	$.getJSON("data/restaurant.json",function(json) {
		var queryString = decodeURIComponent(window.location.search);
		queryString = queryString.substring(1);
		list=queryString.split("&");
		hotel=list[0].split("=")[1];
		var yelp_id;
		var opendata_id;
		$.each( json.restaurants, function(i, item ) {
			if(item.name==hotel){
				yelp_id="yelpId="+item.yelp_id;
				opendata_id="opendataId="+item.opendata_id;
			}
  	});
		response="/search?"+yelp_id+"&"+opendata_id+"&"+list[1];
		// console.log(response);
		// $.fn.delegateJSONResult(response);
		// return response;
		// };
	});
	// console.log(response);
  // return response;
};

function getResults() {
	$.getJSON("data/restaurant.json",function(json) {
		var queryString = decodeURIComponent(window.location.search);
		// console.log(queryString);
		queryString = queryString.substring(1);
		list=queryString.split("&");
		hotel=list[0].split("=")[1];
		var yelp_id;
		var opendata_id;
		$.each( json.restaurants, function(i, item ) {
			if(item.name==hotel){
				yelp_id="yelpId="+item.yelp_id;
				opendata_id="opendataId="+item.opendata_id;
			}
  	});

		var query="/search?"+yelp_id+"&"+opendata_id+"&"+list[1];
		console.log(query);
		$.ajax({
	    url: query,
	    type: 'GET',
			contentType: 'application/json; charset=utf-8',
	    success: function(resultData) {
				var sentiment;
				if(resultData.score >= 0.7){
					sentiment='img/happy.png'
				} else if (resultData.score < 0.7 && resultData.score >= 0.4) {
					sentiment='img/neutral.png'
				} else {
					sentiment='img/sad.png'
				}
				$('#results').append(
					$('<li>').append(
							$('<div>').attr('class','contentHolder')
								.append($('<div>').attr('class', 'photoCol')
									.append($('<img>').attr('src',resultData.imageUrl).attr('class','dispImage'))
									) //can add pic
								.append($('<div>').attr('class', 'infoCol')
									.append($('<div>').attr('class','hotel')
										.append($('<div>').attr('class','name').append(resultData.name))
										.append($('<div>').attr('class','logo').append($('<img>').attr('src','img/yelp.png').attr('class','yelp').attr('onclick','loadReview()')))
										.append($('<div>').attr('class','rating').append(resultData.rating))
										.append($('<div>').attr('class','break').append('a'))
										.append($('<div>').attr('class','sfo').append($('<img>').attr('src','img/sfo.png').attr('class','yelp').attr('onclick','loadViolation()')))
										.append($('<div>').attr('class','rating').append(resultData.riskCategory))
										.append($('<div>').attr('class','break').append('a'))
										.append($('<div>').attr('class','logo').append($('<img>').attr('src',sentiment).attr('class','yelp')))
										.append($('<div>').attr('class','rating').append(resultData.score))
									))
								).attr('class','result leftLayout'));
	    }
	  });
	});
}

function loadReview(){
	$("#reviews").html("");
	$.getJSON("data/restaurant.json",function(json) {
		var queryString = decodeURIComponent(window.location.search);
		queryString = queryString.substring(1);
		list=queryString.split("&");
		hotel=list[0].split("=")[1];
		var yelp_id;
		var opendata_id;
		$.each( json.restaurants, function(i, item ) {
			if(item.name==hotel){
				yelp_id="yelpId="+item.yelp_id;
				opendata_id="opendataId="+item.opendata_id;
			}
  	});
		var query="/search?"+yelp_id+"&"+opendata_id+"&"+list[1];
		$.ajax({
	    url: query,
	    type: 'GET',
			contentType: 'application/json; charset=utf-8',
	    success: function(resultData) {
				// console.log(resultData);
				// console.log(resultData.reviews);
				var reviews=resultData.reviews;
				// console.log(reviews.length);
				for(i=0; i<reviews.length; i++){
					// console.log(reviews[i].text);
					$('#reviews').append(
						$('<li>').append(
								$('<div>').attr('class','contentHolder')
									.append($('<div>').attr('class', 'reviews')
										.append(reviews[i].text))
									).attr('class','result leftLayout'));
				}
	    }
	  });
	});

}

function loadViolation(){
	$("#reviews").html("");
	$.getJSON("data/restaurant.json",function(json) {
		var queryString = decodeURIComponent(window.location.search);
		queryString = queryString.substring(1);
		list=queryString.split("&");
		hotel=list[0].split("=")[1];
		var yelp_id;
		var opendata_id;
		$.each( json.restaurants, function(i, item ) {
			if(item.name==hotel){
				yelp_id="yelpId="+item.yelp_id;
				opendata_id="opendataId="+item.opendata_id;
			}
  	});
		var query="/search?"+yelp_id+"&"+opendata_id+"&"+list[1];
		$.ajax({
	    url: query,
	    type: 'GET',
			contentType: 'application/json; charset=utf-8',
	    success: function(resultData) {
				var reviews=resultData.violation;
				$('#reviews').append(
					$('<li>').append(
							$('<div>').attr('class','contentHolder')
								.append($('<div>').attr('class', 'reviews')
									.append(reviews))
								).attr('class','result leftLayout'));
	    }
	  });
	});
}

// Auto complete function
$( function() {
	$.getJSON("data/restaurant.json", function(json) {
		var items = [];
		$.each( json.restaurants, function(i, item ) {
			items.push(i,item.name);
  	});
		$( "#form1" ).autocomplete({
			source: items
		});
	});
});
