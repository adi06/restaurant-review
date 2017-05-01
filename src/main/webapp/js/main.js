
$(document).ready(function(){
	getResults();
});

function myMap() {
  var myCenter = new google.maps.LatLng(37.7749,-122.4194);
	// var myCenter = new google.maps.LatLng(37.33635,-121.894766);
  var mapCanvas = $('#map')[0];
  var mapOptions = {center: myCenter, zoom: 10};
  var map = new google.maps.Map(mapCanvas, mapOptions);
  var marker = new google.maps.Marker({position:myCenter});
  marker.setMap(map);
}

function makeUrl() {
	var queryString = decodeURIComponent(window.location.search);
	queryString = queryString.substring(1);
  return "/search?" + queryString;
}

function doAjax(_url) {
  return $.ajax({
    url: _url,
    type: 'GET'
  });
}

function getResults() {
	var queryString = decodeURIComponent(window.location.search);
	queryString = queryString.substring(1);
	var queries = queryString.split("&");
	var res=queries[0].split("=");
	var hotel=res[1];
	doAjax(makeUrl())
	for (i = 0; i <20; i++) {
		$('#results').append(
	    $('<li>').append(
	        $('<div>').attr('class','contentHolder')
						.append($('<div>').attr('class', 'photoCol')) //can add pic
						.append($('<div>').attr('class', 'infoCol')
							.append($('<div>').attr('class','hotel')
								.append($('<div>').attr('class','name').append(res[1]))
								.append($('<div>').attr('class','rating').append('3.6'))
							))
						).attr('class','result leftLayout'));
	}
}



// <li <div class="infoCol"><div class="venueBlock"><div class="venueDetails"><div class="venueScore positive" title="9.3/10 - People like this place" style="background-color: #00B551;">9.3</div><div class="venueName"><h2><span class="venueIndex">1</span>. <a href="/v/four-peaks-brewing-company/4a3ad368f964a52052a01fe3" target="_blank">Four Peaks Brewing Company</a></h2></div><div class="venueMeta"><div class="venueAddressData"><div class="venueData"><span class="venueDataItem"><span class="categoryName">Brewery</span><span class="delim"> &bull; </span></span><span class="venueDataItem"><span class="price" title="Moderate"><span class="darken" itemprop="priceRange">$$</span>$$</span><span class="delim"> &bull; </span></span><span class="linkToMenu venueDataItem">
// <a class="menuLink" href="https://foursquare.com/v/four-peaks-brewing-company/4a3ad368f964a52052a01fe3/menu" target="_blank">View Menu</a><span class="delim"> &bull; </span></span></div><div class="venueAddress">1340 E 8th St (at Dorsey Ln), Tempe</div></div></div></div></div><div class="resultFooter"><div class="buttons"><div class="save-button  inactive"><span class="icon"></span><span class="label">Save</span></div></div></div></div><div class="detail tips "><ul class="tips"><li class="tip"><div class="avatar"><img src="https://igx.4sqi.net/img/user/64x64/5GYUCRZX43ECYFID.jpg" alt="Tom S." class="avatar "width="64" height="64"title="Tom S."data-retina-url="https://igx.4sqi.net/img/user/128x128/5GYUCRZX43ECYFID.jpg" /></div><p class="tipText"><span class="tipAuthor "><a class="userName" href="/ritalynman" target="_blank">Tom S.</a> &bull; August 20, 2011</span>Everything on the menu is great, and I highly recommend the Peach beer.  Pretty good for beer, considering I hate the stuff!  (Except Guiness)</p></li></ul></div></div></li>

function ref(){
	$('#results ul').append(
    $('<li>').append(
        $('<div>').attr('class','contentHolder').append(
					$('<div>').attr('class', 'photoCol') //can add pic
					)).attr('class','result'));
}


function bigImg(x) {
   x.style.height = "17px";
	 x.style.width = "17px";
};

function normalImg(x) {
    x.style.height = "10px";
    x.style.width = "10px";
};
