import { StatusBar } from 'expo-status-bar';
import { Button, StyleSheet, Text, TextInput, View } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [valor1,setValor1] = useState("0")
  const [valor2,setValor2] = useState("0")
  const [resultado,setResultado] = useState(0)

  const recuperarEntero = (Componente) => {
    let valorEnt = parseInt(Componente);
    return valorEnt
  }

  const sumar = () => {
    let valor1Ent = recuperarEntero(valor1)
    let valor2Ent = recuperarEntero(valor2)
    let resultado;
    resultado = valor1Ent + valor2Ent;
    return resultado;
  }

  const restar = () => {
    let valor1Ent = recuperarEntero(valor1)
    let valor2Ent = recuperarEntero(valor2)
    let resultado;
    resultado = valor1Ent - valor2Ent;
    return resultado;
  }

  const multiplicar = () => {
    let valor1Ent = recuperarEntero(valor1)
    let valor2Ent = recuperarEntero(valor2)
    let resultado;
    resultado = valor1Ent * valor2Ent;
    return resultado;
  }

  return (
    <View style={styles.container}>
      <Text>Calculadora</Text>
      <TextInput
        style={styles.cajaTexto}
        value={valor1}
        onChangeText={(valor1) => setValor1(valor1)}
        />
      <TextInput
        style={styles.cajaTexto}
        value={valor2}
        onChangeText={(valor2) => setValor2(valor2)}
      />

      <Button
        title='SUMAR'
        onPress={() => {
          let resultado = sumar()
          setResultado(resultado)
        }}
      />
      <Button
        title='RESTAR'
        onPress={() => {
          let resultado = restar()
          setResultado(resultado)
        }}
      />
      <Button
        title='MULTIPLICAR'
        onPress={() => {
          let resultado = multiplicar()
          setResultado(resultado)
        }}
      />

        <Text>Resultado: {resultado}</Text>
      <StatusBar style="auto"/>
      
    </View>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  cajaTexto:{
    borderColor: "black",
    borderWidth: 1,
    paddingTop:3,
    paddingBottom:3,
    paddingHorizontal:10,
    margin: 10
  },

});
