$( document ).ready(function() {

    var generationsCountdown = 20;
    const generationsDelayInSeconds = 2;
    
    const generationInterval = setInterval(fetchNextGeneration, generationsDelayInSeconds*1000);

    function fetchNextGeneration() {
        fetch("http://localhost:9000/game")
            .then(resp => resp.json())
            .then(generation => {
                console.log(generation);
                mountUniverseGrid(generation);
                generationsCountdown--;    
                if (generationsCountdown === 0) 
                    clearInterval(generationInterval);
            });
    }

    function mountUniverseGrid(generation) {
        $( ".grid" ).empty();
        for(let i=0; i<generation.length; i++) {
            let newRow = $("<tr></tr>");
            const row = generation[i];
            for(let j=0; j<generation.length; j++) {
                const col = row[j];
                const className = col === 0 ? "dead" : "alive";
                newRow.append(`<td class=${className}></td>`);            
            }
            $(".grid").append(newRow);
        }
    }

});
