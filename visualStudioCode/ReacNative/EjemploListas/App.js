import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, FlatList, TextInput, Button, Alert } from 'react-native';
import { useState } from 'react'

let personas = [
  { nombre: "Thor", apellido: "Thillas", cedula: "0243568855" },
  { nombre: "Amber", apellido: "Flores", cedula: "9848748948" },
  { nombre: "Peter", apellido: "Parker", cedula: "7211455458" },
]

let esNuevo = true;
let indiceSeleccionado = -1;

export default function App() {
  const [txtCedula, setCedula] = useState(0);
  const [txtNombre, setNombre] = useState(0);
  const [txtApellido, setApellido] = useState(0);
  const [numElementos, setNumElementos] = useState(personas.length)

  let limpiar = () => {
    setCedula(null);
    setNombre(null);
    setApellido(null);
    esNuevo = true
  }

  let existenPersonas = () => {
    for (let i = 0; i < personas.length; i++) {
      if (personas[i].cedula == txtCedula) {
        return true;
      }
    }
    return false;
  }

  let guardarPersona = () => {
    if (esNuevo) {
      if (existenPersonas()) {
        Alert.alert("INFO","Ya existe una persona con la cedula "+txtCedula)
      } else {
        let nuevaPersona = { nombre: txtNombre, apellido: txtApellido, cedula: txtCedula }
        personas.push(nuevaPersona);
      }
    } else {
      personas[indiceSeleccionado].nombre = txtNombre;
      personas[indiceSeleccionado].apellido = txtApellido;
    }
    limpiar()
    setNumElementos(personas.length)
  }

  let ItemPersona = (props) => {
    return (
      <View style={styles.itemPersona}>
        <View style={styles.itemPersona1}>
          <Text style={styles.textoPrincipal}>{props.indice}</Text>
        </View>
        <View style={styles.itemPersona2}>
          <Text style={styles.textoPrincipal}>{props.persona.nombre} {props.persona.apellido}</Text>
          <Text style={styles.textoSecundario}>{props.persona.cedula}</Text>
        </View>
        <View style={styles.itemBotones}>
          <Button title="✏️" onPress={() => {
            setCedula(props.persona.cedula);
            setNombre(props.persona.nombre);
            setApellido(props.persona.apellido);
            esNuevo = false;
            indiceSeleccionado = props.indice;
          }}
          />
          <Button
            title="❌"
            onPress={() => {
              indiceSeleccionado = props.indice
              personas.splice(indiceSeleccionado, 1);
              setNumElementos(personas.length)
            }} />
        </View>
      </View>

    );
  }

  return (
    <View style={styles.container}>
      <View style={styles.areaCabecera}>
        <Text>Personas</Text>

        <TextInput
          style={styles.txt}
          value={txtCedula}
          placeholder='Ingrese su cédula'
          onChangeText={(txtCedula) => setCedula(txtCedula)}
          keyboardType='numeric'
          editable={esNuevo}
        />
        <TextInput
          style={styles.txt}
          value={txtNombre}
          placeholder='Ingrese su Nombre'
          onChangeText={(txtNombre) => setNombre(txtNombre)}
        />
        <TextInput
          style={styles.txt}
          value={txtApellido}
          placeholder='Ingrese su Apellido'
          onChangeText={(txtApellido) => setApellido(txtApellido)}
        />

        <View style={styles.areaBotones}>
          <Button
            title='Guardar'
            onPress={() => { guardarPersona() }}
          />
          <Button
            title='Nuevo'
            onPress={() => { limpiar() }}
          />
        </View>

        <View style={styles.text}>
          <Text style={styles.text}>Elementos: {numElementos}</Text>
        </View>
      </View>

      <View style={styles.areaContenido}>
        <FlatList
          style={styles.lista}
          data={personas}
          renderItem={(obj) => {
            return <ItemPersona
              indice={obj.index}
              persona={obj.item}
            />
          }}
          keyExtractor={(item) => { return item.cedula }}
        />
      </View>

      <View style={styles.areaPie}>
        <Text>Sebastián Chamorro</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    //backgroundColor: 'lightblue',
    flexDirection: 'column',
    alignItems: 'stretch',
    justifyContent: 'flex-start',
    paddingHorizontal: 10,
    paddingTop: 100
  },
  lista: {
    //backgroundColor: "lightpink",
  },
  itemPersona: {
    //backgroundColor: "lightyellow",
    marginBottom: 5,
    padding: 5,
    flexDirection: 'row'
  },
  itemPersona1: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: "lightblue",
    padding: 5,
  },
  itemPersona2: {
    flex: 5,
    backgroundColor: "lightblue",
    padding: 5,
    paddingLeft: 30,
  },
  itemBotones: {
    backgroundColor: "lightblue",
    flex: 3,
    flexDirection: 'row',
    alignItems: "center",
    justifyContent: "space-evenly",
  },
  textoPrincipal: {
    fontSize: 20,
  },
  textoSecundario: {
    fontSize: 15,
    fontStyle: 'italic'
  },
  areaCabecera: {
    flex: 4,
    //backgroundColor: "chocolate",
    justifyContent: 'center',
  },
  areaContenido: {
    flex: 6,
    //backgroundColor: "lightblue",
  },
  areaPie: {
    flex: 1,
    //backgroundColor: "lightpink",
    justifyContent: 'center',
    alignItems: 'flex-end'
  },
  txt: {
    borderWidth: 1,
    borderColor: "gray",
    paddingTop: 3,
    paddingHorizontal: 5,
    marginBottom: 5
  },
  areaBotones: {
    flexDirection: 'row',
    margin: 5,
    justifyContent: "space-evenly"
  },
  text: {
    fontSize: 15,
    justifyContent: 'flex-end',
    alignContent: 'flex-end',
    paddingLeft: 115,
    paddingVertical: 5
  }
})
