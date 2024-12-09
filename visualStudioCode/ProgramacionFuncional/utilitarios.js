/*recuperarTexto = function(idComponente) {
    let componente = document.getElementById(idComponente);
    let valorComp = componente.value;
    return valorComp;
}*/

//Arrow Functions
recuperarTexto = (idComponente) => {
    let componente = document.getElementById(idComponente);
    let valorComp = componente.value;
    return valorComp;
}

recuperarEntero = (idComponente) => {
    let valorTxt = recuperarTexto(idComponente);
    let valorEnt = parseInt(valorTxt);
    return valorEnt
}

recuperarFloat = (idComponente) => {
    let valorTxt = recuperarTexto(idComponente);
    let valorFloat = parseFloat(valorTxt);
    return valorFloat
}