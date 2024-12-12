import { StatusBar } from 'expo-status-bar';
import { Button, StyleSheet, Text, TextInput, View } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [nombre, setNombre] = useState();
  const [apellido, setApellido] = useState();


  return (
    <View style={styles.container}>
      <Text style={styles.titulo} >Ejemplo Margin</Text>
      <TextInput
        style={styles.caja} 
        value={nombre}
        onChange={setNombre}
        placeholder='Ingrese su nombre'
      />
      <TextInput
        style={styles.caja} 
        value={apellido}
        onChange={setApellido}
        placeholder='Ingrese su apellido'
      />
      <Button
        title='OK'
      />
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    flexDirection: 'column', 
    justifyContent: 'center',
    alignItems: 'stretch',
    paddingHorizontal: 50
  },
  caja : {
    borderColor: 'gris',
    borderWidth: 1,
    padding: 5,
    paddingHorizontal: 10,
    marginBottom: 10
  },
  titulo: {
    fontSize: 20,
    marginBottom: 10,
    fontWeight: "bold"
  }
});
