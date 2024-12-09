ejecutarOperacion = (operar) => {
    let valor1 = recuperarEntero("txtValor1");
    let valor2 = recuperarEntero("txtValor2");
    let resultado;
    resultado = operar(valor1, valor2);
    console.log(resultado);
}

sumar = (sum1, sum2) => {
    let resultado;
    resultado = sum1 + sum2;
    return resultado;
}

restar = (rest1, rest2) => {
    let resultado;
    resultado = rest1 - rest2;
    return resultado;
}







imprimir = () => {
    console.log("Hola mundo");
}

saludar = () => {
    alert("Aprendiendo programacion funcional")
}

ejecutar = (fn) => {
    console.log("estoy ejecutando funciones...")
    fn();
}

testEjecutar = () => {
    ejecutar(imprimir);
    ejecutar(saludar);
    ejecutar(() => {alert("Soy una Funcion sin Nombre")});
}