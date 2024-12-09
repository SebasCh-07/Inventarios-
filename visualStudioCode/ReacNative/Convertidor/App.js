import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TextInput, Button} from 'react-native';
import { useState } from 'react';

export default function App() {
  const [dolar,setDolar] = useState("$")
  const [valor1,setValor1] = useState("")

  const recuperarFloat = (Componente) => {
    let valorEnt = parseFloat(Componente);
    return valorEnt
  }

  const ConvertirPesosMX = () => {
    let valor1Ent = recuperarFloat(dolar)
    let resultado;
    resultado = valor1Ent * 20.19;
    return resultado;
  }

  const ConvertirPesosCOL = () => {
    let valor1Ent = recuperarFloat(dolar)
    let resultado;
    resultado = valor1Ent * 4432.88;
    return resultado;
  }

  const Euros = () => {
    let valor1Ent = recuperarFloat(dolar)
    let resultado;
    resultado = valor1Ent * 0.95;
    return resultado;
  }


  return (
    <View style={styles.container}>
      <Text style={styles.texto}>Convertidor</Text>
      <Text style={styles.texto}>INGRESE LOS DOLARES</Text>
      <TextInput
        style={styles.cajaTexto}
        value={dolar}
        onChangeText={(text) => setDolar(text)}
        />

        <Text style={styles.texto}>Dólares a: {valor1}</Text>

      <Button
        title='PESOS MEXICANOS'
        onPress={() => {setValor1("Pesos Mexicanos: "+ConvertirPesosMX()+"$")}}
      />
      <Button
        title='PESOS COLOMBIANOS'
        onPress={() => {setValor1("Pesos Colombianos: "+ConvertirPesosCOL()+"$")}}
      />
      <Button
        title='EUROS'
        onPress={() => {setValor1("Euros: "+Euros()+"$")}}
      />

      <StatusBar style="auto" />
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
    paddingHorizontal:50,
    marginHorizontal: 10,
    marginBottom: 10
  },
  texto:{
    margin: 5,
    marginTop: 10
  },
});
