// script.js
document.addEventListener("DOMContentLoaded", function () {
    const startLocationInput = document.getElementById("start-location");
    const endLocationInput = document.getElementById("end-location");
    const calculateButton = document.getElementById("calculate-button");
    const distanceElement = document.getElementById("distance");

    let map;
    let directionsService;
    let directionsDisplay;

    function initMap() {
        map = new google.maps.Map(document.getElementById("map"), {
            center: { lat: 0, lng: 0 },
            zoom: 10,
        });
        directionsService = new google.maps.DirectionsService();
        directionsDisplay = new google.maps.DirectionsRenderer();
        directionsDisplay.setMap(map);
    }

    calculateButton.addEventListener("click", function () {
        const startLocation = startLocationInput.value;
        const endLocation = endLocationInput.value;

        if (!startLocation || !endLocation) {
            alert("Please enter both start and end locations.");
            return;
        }

        calculateDistance(startLocation, endLocation);
    });

    function calculateDistance(start, end) {
        const request = {
            origin: start,
            destination: end,
            travelMode: "DRIVING",
        };

        directionsService.route(request, function (result, status) {
            if (status === "OK") {
                const distance = result.routes[0].legs[0].distance.text;
                const duration = result.routes[0].legs[0].duration.text;
                distanceElement.textContent = `Distance: ${distance}, Duration: ${duration}`;

                const startMarker = new google.maps.Marker({
                    position: result.routes[0].legs[0].start_location,
                    map: map,
                    title: "Start Location",
                });

                const endMarker = new google.maps.Marker({
                    position: result.routes[0].legs[0].end_location,
                    map: map,
                    title: "End Location",
                });

                const bounds = new google.maps.LatLngBounds();
                bounds.extend(startMarker.getPosition());
                bounds.extend(endMarker.getPosition());
                map.fitBounds(bounds);
            } else {
                distanceElement.textContent = "Error calculating distance.";
            }
        });
    }

 
    
});
