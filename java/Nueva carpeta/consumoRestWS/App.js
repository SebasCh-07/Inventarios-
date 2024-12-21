import { NavigationContainer } from '@react-navigation/native'
import {createNativeStackNavigator} from '@react-navigation/native-stack'
import {TestWebServices} from './screens/TestWebServices'
export default function App() {
  const StackProducts = createNativeStackNavigator(); 
  return (
    <NavigationContainer>
      <StackProducts.Navigator>
        <StackProducts.Screen name="TestWebServicesNav"  component={TestWebServices}/>
      </StackProducts.Navigator>
    </NavigationContainer>
  );
}
