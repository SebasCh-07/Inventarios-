import { StyleSheet, Text, View, Button, TouchableOpacity } from 'react-native';

export const Productos = ({navigation}) =>{
    return <View style={styles.container}>
        <Text>Products</Text>
        <Button 
        title="Go to Home" 
        onPress={() => {
          navigation.navigate('Home');
        }} />
    </View>
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
  });