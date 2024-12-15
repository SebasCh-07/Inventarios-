import { View, StyleSheet, Text, FlatList, TouchableHighlight } from "react-native"
import { getGrades } from '../services/GradesSevices'
import { Avatar, FAB, ListItem } from '@rneui/base'
import { useState } from 'react'

export const ListGrades = ({ navigation }) => {
  const [time, setTime] = useState()

  const refreshList = () => {
    setTime(new Date().getTime())
  }

  const ItemGrade = ({ nota }) => {
    return <TouchableHighlight onPress={() => {
      navigation.navigate("GradeForm", { Notas: nota, fnRefresh: refreshList });
    }}>
      <ListItem bottomDivider>
        <Avatar
          title={nota.subject.substring(0, 1)}
          containerStyle={{ backgroundColor: '#a763e7' }}
          rounded
        />
        <ListItem.Content>
          <ListItem.Title>{nota.subject}</ListItem.Title>
        </ListItem.Content>
        <ListItem.Content>
          <ListItem.Title>{nota.grade}</ListItem.Title>
        </ListItem.Content>
        <ListItem.Chevron />
      </ListItem>
    </TouchableHighlight>
  }
  return <View style={styles.container}>
    <FlatList
      data={getGrades()}
      renderItem={({ item }) => {
        return <ItemGrade nota={item} />
      }}
      keyExtractor={item => item.subject}
      extraData={time}
    />
    <FAB
      title="+"
      placement="right"
      onPress={() => { navigation.navigate("GradeForm", { Notas: null, fnRefresh: refreshList }) }}
    />
  </View>
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'stretch',
    justifyContent: 'center',
  },
});
