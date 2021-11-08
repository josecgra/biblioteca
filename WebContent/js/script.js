/**
 * Fichero JS para la biblioteca
 */

function desaparecer() {
    var op = 0.1;
    var increment = +0.1;
	var sp = document.getElementById("msginfo");
    sp.style.opacity = 0;
    sp.style.display = 'inline';
	sp.style.backgroundColor='#04AA6D';
	sp.style.color = 'white';

    var timer = setInterval(function() {
        op += increment;
        sp.style.opacity = op;
        if (op >= 1) increment = -increment;
        if (op <= 0) {
            sp.style.display = 'none';
            clearInterval(timer); 
        }
    }, 100);
}

desaparecer();