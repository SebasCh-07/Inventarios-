import { StatusBar } from 'expo-status-bar';
import { Button, StyleSheet, Text, View } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.contenedor2}></View>
      <View style={styles.contenedor3}>
        <View style={styles.contenedor4}></View>
        <View style={styles.contenedor5}>
          <View style={styles.contenedor6}></View>
          <View style={styles.contenedor7}>
            <Button
              title='B1'
              color="green"
            />
            <Button
              title='B2'
              color="blue"
            />
            <Button
              title='B3'
            />
          </View>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'gray',
    flexDirection: "column"
  },
  contenedor2:{
    flex: 1,
    backgroundColor: 'blue',
  },
  contenedor3:{
    flex: 3,
    backgroundColor: 'green',
  },
  contenedor4:{
    flex: 1,
    backgroundColor: 'yellow',
  },
  contenedor5:{
    flex: 1,
    backgroundColor: 'white',
    flexDirection: "row"
  },
  contenedor6:{
    flex: 1,
    backgroundColor: 'red',
    flexDirection: "row"
  },
  contenedor7:{
    flex: 2,
    backgroundColor: "skyblue",
    flexDirection: "column",
    alignItems: "stretch",
    justifyContent: "space-around"
  },

});
