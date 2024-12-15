import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Alert } from 'react-native';
import { Button, Icon, Input } from '@rneui/base'
import {useState} from 'react'

export default function App() {
  const [name, setName] = useState()
  return (
    <View style={styles.container}>
      <Text>RNE</Text>

      <Input
        value={name}
        onChangeText={setName}
        placeholder="Ingrese su nombre"
        label = "Nombre"
      />

      <Text>{name}</Text>

      <Button
        title="OK"
        icon={{
          name: 'instagram',
          type: 'zocial',
          size: 20,
          color: 'white',
        }}
        onPress={() => Alert.alert("Info", "Su nombre es "+name)}
      />
      <Button
        title="Cancel"
        icon={<Icon
          name='youtube'
          type='zocial'
          color='white'
        />}
      />

      <Icon
        name='youtube'
        type='zocial'
        color='red'
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
});
