/**
 * Fichero JS para la biblioteca
 */

function desaparecer() {
    var op = 0.1;
    var increment = +0.1;
	var sp = document.getElementById("msginfo");
    sp.style.opacity = 0;
	sp.style.visibility = 'visible';
	sp.style.backgroundColor='#ffcece';
	sp.style.color = 'red';

    var timer = setInterval(function() {
        op += increment;
        sp.style.opacity = op;
        if (op >= 1) increment = -increment;
        if (op <= 0) {
			sp.style.visibility = 'hidden';
            clearInterval(timer); 
        }
    }, 120);
}

desaparecer();