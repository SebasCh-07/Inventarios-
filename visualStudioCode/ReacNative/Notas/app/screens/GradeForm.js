import { View, StyleSheet, Text, FlatListComponent } from "react-native"
import {Input, Button} from '@rneui/base'
import {useState} from 'react'
import {saveGrade, updateGrade} from '../services/GradesSevices'

export const GradeForm = ({navigation,route}) => {
  let isNew = true
  let subjectR;
  let gradeR;
  if(route.params.Notas != null){
    isNew = false
  }
  if (!isNew){
    subjectR = route.params.Notas.subject
    gradeR = route.params.Notas.grade
  }
  const [subject, setSubject] = useState(subjectR)
  const [grade, setGrade] = useState(gradeR==null?null:gradeR+"")
  const [errorSubj, setErroSubj] = useState()
  const [errorGrade, setErroGrade] = useState()
  let hasErrors = false;

  const save = () => {
    setErroGrade(null)
    setErroSubj(null)
    validate()
    if (hasErrors == false) {
      if (isNew) {
        saveGrade({subject: subject, grade: grade})
      }else{
        updateGrade({subject: subject, grade: grade})
      }
      navigation.goBack()
      route.params.fnRefresh()
    }
  }

  const validate = () => {
    if (subject == null || subject == "") {
      setErroSubj("Debe ingresar una materia")
      hasErrors = true
    }
    let gradeFloat = parseFloat(grade)
    if (grade == null || isNaN(gradeFloat) || gradeFloat<0 || gradeFloat>10) {
      setErroGrade("Debe ingresar una nota entre 0 - 10")
      hasErrors = true
    }
  }
    return <View style={styles.container}>
        <Input
          value={subject}
          onChangeText={(subject)=> {setSubject(subject)}}
          placeholder="Ejemplo: Matematicas"
          label = 'Materia'
          errorMessage={errorSubj}
          disabled={!isNew}
        />
        <Input
          value={grade}
          onChangeText={(grade)=> {setGrade(grade)}}
          placeholder="0-10"
          label = 'Nota'
          errorMessage={errorGrade}
        />
        <Button 
        title="Guardar" 
        icon= {{
          name: 'save',
          type: 'entypo',
          size: 24,
          color: 'white',
        }}
        buttonStyle = {styles.saveBoton}
        onPress={save} />
    </View>
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
    saveBoton: {
      backgroundColor: 'green',

    }
  });
  