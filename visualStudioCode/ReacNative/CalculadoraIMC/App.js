import { StatusBar } from 'expo-status-bar';
import { Button, StyleSheet, Text, TextInput, View } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [estatura, setEstatura] = useState('')
  const [peso, setPeso] = useState('')
  const [resultado, setResultado] = useState("")  
  const [msg, setMsg] = useState("")  

  const recuperarFloat = (Componente) => {
    let valorEnt = parseFloat(Componente);
    return valorEnt
  }

  const CalcularIMC = () => {
    let estaturaInt = (recuperarFloat(estatura)/100)
    let pesoInt = recuperarFloat(peso)
    let resultado;
    resultado = pesoInt / (estaturaInt*estaturaInt);
    return resultado;
  }

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>CALCULADORA DE IMC</Text>

      <View style={styles.viewDatos}>
        <Text style={styles.tituloDatos}>ESTATURA</Text>
        <Text style={styles.subtitulo}>en centímetros</Text>
        <TextInput
          style={styles.input}
          value={estatura}
          onChangeText={(estatura) => setEstatura(estatura)}
          placeholder='170'
        />
  
        <Text style={styles.tituloDatos}>PESO</Text>
        <Text style={styles.subtitulo}>en kilogramos</Text>
        <TextInput
          style={styles.input}
          value={peso}
          onChangeText={(peso) => setPeso(peso)}
          placeholder='100'
        />
      </View>    
      
      <View style={styles.viewDatosResult}>
      <Button
        title='CALCULAR'
        onPress={() => {
          let resultado = CalcularIMC();
          setResultado(resultado.toFixed(2));
          if (resultado < 18.5) {
            setMsg("Peso inferior al normal")
          }else if(resultado >= 18.5 && resultado <= 24.9) {
            setMsg("Peso normal")
          }else if(resultado >= 25.0 && resultado <= 29.9) {
            setMsg("Peso superior al normal")
          }else if (resultado >= 30.0) {
            setMsg("Obesidad")
          }
        }}
        color="green"
      />
        <View style={styles.viewResult}>
          <Text style={styles.resultado}>Su IMC es: {resultado}</Text>
          <Text style={styles.resultado}>{msg}</Text>
        </View>
        

      </View>
      
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#d1f2eb',
    alignItems: 'center',
    justifyContent: 'center',
    padding:20
  },
  titulo : {
    fontSize: 30,
    color: 'green',
    fontWeight: '900',
    marginTop: 30,
    marginBottom: 50
  },
  viewDatos : {
    flex: 1,
    flexDirection: 'column',
    alignItems: 'flex-start',
    justifyContent: 'center',
  },
  viewDatosResult: {
    flex: 1,
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'flex-start',
    marginTop: 30,
  },
  input: {
    borderColor: "black",
    marginHorizontal: 10,
    marginVertical: 15,
    borderWidth: 1,
    paddingRight: 90,
    paddingLeft: 10,
    paddingTop:3,
    paddingBottom:3,
    backgroundColor: "white"
  },
  tituloDatos: {
    fontSize: 15,
    textDecorationLine: 'underline',
    color: 'black',
    fontWeight: 'bold',
  },
  subtitulo: {
    color: "gray",
    fontWeight: '500'
  },
  resultado: {
    fontSize: 15,
    color: 'black',
    fontWeight: '600',
  },
  viewResult: {
    paddingTop: 15,
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
  }

});
