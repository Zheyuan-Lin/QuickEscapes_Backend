document.getElementById('flightForm')
    .addEventListener('submit', async (event) => {
    event.preventDefault();




    const flightData = {
        sourceAirportCode: document.getElementById('sourceAirportCode').value,
        destinationAirportCode: document.getElementById('destinationAirportCode').value,
        date: document.getElementById('date').value,
        numAdults: document.getElementById('numAdults').value,
        numSeniors: document.getElementById('numSeniors').value,
        sortOrder: document.getElementById("sortOrder").value,
        classOfService: document.getElementById("classOfService").value,
        pageNumber: document.getElementById("pageNumber").value,
        nonstop: document.getElementById("nonstop").value,
        currencyCode: document.getElementById("currencyCode").value

    };

    try {
        const response = await axios.post('http://localhost:8080/flights/oneway', flightData);
        console.log(response.data);
        //displayFlightResults(response.data);
    } catch (error) {
        console.error('Error fetching flight data:', error);
        document.getElementById('flights').textContent = 'Error fetching flight data';
    }
});

function displayFlightResults(flights) {
    const resultsDiv = document.getElementById('flights');
    resultsDiv.innerHTML = '';  // Clear any previous results

    // Iterate through each flight result and create HTML for each
    flights.forEach(flight => {
        const flightCard = document.createElement('div');
        flightCard.className = 'flight-card';  // Add a class for styling

        flightCard.innerHTML = `
            <h3>Flight from ${flight.originStationCode} to ${flight.destinationStationCode}</h3>
            <p><strong>Departure:</strong> ${formatDate(flight.departureDateTime)}</p>
            <p><strong>Arrival:</strong> ${formatDate(flight.arrivalDateTime)}</p>
            <p><strong>Class:</strong> ${flight.classOfService}</p>
            <p><strong>Flight Number:</strong> ${flight.marketingCarrierCode}-${flight.flightNumber}</p>
            <p><strong>Stops:</strong> ${flight.numStops}</p>
            <p><strong>Distance:</strong> ${flight.distanceInKM} km</p>
            <p><strong>Amenities:</strong> ${flight.amenities ? flight.amenities.join(', ') : 'None'}</p>
            <hr>
        `;

        resultsDiv.appendChild(flightCard);
    });
}
function formatDate(dateTime) {
    const date = new Date(dateTime);
    return date.toLocaleString();  // Format as local date and time
}
