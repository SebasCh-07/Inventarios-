import { View, StyleSheet } from 'react-native'
import { Button, Text } from '@rneui/base'
import {getAllPostsService} from '../services/TestServices'


export const TestWebServices = () => {

  const getAllPosts = () => {
    getAllPostsService();
  }

  return <View style={styles.container}>
    <Text style={styles.textContainer}>Por fin Funciona</Text>
    <View style={styles.buttonContainer}>
      <Button
        title="Recuperar Posts"
        onPress={getAllPosts}
      />
      <Button
        title="Crear Post"
      />
        <Button
        title="Actualizar Post"
      />
        <Button
        title="Filtrar"
      />
          <Button
        title="XXX"
      />

      <Button
        title="YYY"
      />

      <Button
        title="ZZZ"
      />
      
    </View>
  </View>
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    backgroundColor: '#fff',
  },
  textContainer: {
    flex: 1,
    textAlign: 'center',
    fontSize: 18,
    marginVertical: 10
  },
  buttonContainer: {
    flex: 6,
    alignItems: 'stretch',
    justifyContent: 'space-around',
    marginHorizontal:10

  }
});