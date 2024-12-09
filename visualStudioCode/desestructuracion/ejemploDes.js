recuperar = ( ) => {
    let frutas = ["Pera", "Manzana", "sandia"];
    frutas.push("Banana");
    return frutas;
}

testRecuperar = () => {
    let misFrutas = recuperar();
    let frutaPrimera = misFrutas[0];
    let frutaSegunda = misFrutas[1];

    console.log("1>>>>>>"+frutaPrimera);
    console.log("2>>>>>>"+frutaSegunda);
}

testRecuperarDes = () => {
    let  [frutaPrimera, frutaSegunda, frutaTercera] = recuperar();
    console.log("1>>>>>>"+frutaPrimera);
    console.log("2>>>>>>"+frutaSegunda);
    console.log("3>>>>>>"+frutaTercera);

}