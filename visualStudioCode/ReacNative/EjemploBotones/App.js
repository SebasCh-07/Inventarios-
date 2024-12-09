import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button, Alert } from 'react-native';

export default function App() {

  const finalizar = () => {
    Alert.alert("SU SESION HA FINALIZADO")
  }

  return (
    <View style={styles.container}>
      <Text>RETO 20</Text>
      <StatusBar style="auto" />

      <Button
        title='FINALIZAR'
        onPress={finalizar}
      />

      <Button
        title='INICIAR'
        onPress={() => {Alert.alert('SU SESION HA INICIADO')}}
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
