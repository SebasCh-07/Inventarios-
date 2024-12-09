import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button, Alert } from 'react-native';

export default function App() {

  const despedirse = () => {
    Alert.alert('MENSAJE', 'Hasta luego');
  }

  return (
    <View style={styles.container}>
      <Text>Bienvenido al curso de React Native yo soy Sebastián Chamorro</Text>
      <StatusBar style="auto" />
      <Button
        title="HOLA"
        onPress={() => {Alert.alert("Mensaje","Hola desde el botón")}}
      />
      <Button
        title='ADIOS'
        onPress={despedirse}
      />
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
});
