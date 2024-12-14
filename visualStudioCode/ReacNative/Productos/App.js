import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, FlatList, TextInput, Button, Alert, ScrollView, Modal, TouchableOpacity } from 'react-native';
import { useState } from 'react';

let productos = [
  { nombre: "Doritos", categoria: "Snacks", precioCompra: 0.40, precioVenta: 0.45, id: 100 },
  { nombre: "Manicho", categoria: "Golosinas", precioCompra: 0.20, precioVenta: 0.25, id: 101 },
  { nombre: "Rufles", categoria: "Snacks", precioCompra: 0.40, precioVenta: 0.55, id: 102 },
  { nombre: "Coca Cola", categoria: "Bebida", precioCompra: 0.53, precioVenta: 0.65, id: 103 },
  { nombre: "Fuze Tea", categoria: "Bebida", precioCompra: 0.45, precioVenta: 0.50, id: 104 },
];

let esNuevo = true;
let indiceSeleccionado = -1;

export default function App() {
  const [id, setId] = useState("");
  const [precioVenta, setPrecioVenta] = useState("");
  const [nombre, setNombre] = useState("");
  const [categoria, setCategoria] = useState("");
  const [precioCompra, setPrecioCompra] = useState("");
  const [numElementos, setNumElementos] = useState(productos.length);
  const [modalVisible, setModalVisible] = useState(false);

  let limpiar = () => {
    setNombre(null);
    setCategoria(null);
    setPrecioCompra(null);
    setPrecioVenta(null);
    setId(null);
    esNuevo = true;
  };

  let existenProductos = () => {
    for (let i = 0; i < productos.length; i++) {
      if (productos[i].id == id) {
        return true;
      }
    }
    return false;
  };

  let calcularPrecioVenta = () => {
    let precioComprafloat = parseFloat(precioCompra);
    if (!isNaN(precioComprafloat)) {
      let precioVentaCalculado = precioComprafloat + (precioComprafloat * 0.2);
      setPrecioVenta(precioVentaCalculado.toFixed(2));
    } else {
      setPrecioVenta("");
    }
  };

  let guardarProductos = () => {
    if (esNuevo) {
      if (existenProductos()) {
        Alert.alert("INFO", "Ya existe un producto con ID " + id);
        return;
      } else if (id == "" || nombre == "" || categoria == "" || precioCompra == "") {
        Alert.alert("INFO", "Debe llenar todos los campos");
        return;
      } else {
        let nuevaProducto = { nombre: nombre, categoria: categoria, precioCompra: precioCompra, precioVenta: precioVenta, id: id };
        productos.push(nuevaProducto);
        limpiar();
      }
    } else {
      productos[indiceSeleccionado].nombre = nombre;
      productos[indiceSeleccionado].categoria = categoria;
      productos[indiceSeleccionado].precioCompra = precioCompra;
      productos[indiceSeleccionado].precioVenta = precioVenta;
      limpiar();
    }
    setNumElementos(productos.length);
  };

  const eliminarProducto = () => {
    productos.splice(indiceSeleccionado, 1);
    setNumElementos(productos.length);
    setModalVisible(false);
  };

  let ItemProducto = ({ indice, producto }) => {
    return (
      <TouchableOpacity style={styles.itemProducto} onPress={() => {
        setNombre(producto.nombre);
        setCategoria(producto.categoria);
        setPrecioCompra(producto.precioCompra.toString());
        calcularPrecioVenta();
        setId(producto.id.toString());
        esNuevo = false;
        indiceSeleccionado = indice;
      }}>
        <View style={styles.itemProducto1}>
          <Text style={styles.textoPrincipal}>{producto.id}</Text>
        </View>
        <View style={styles.itemProducto2}>
          <Text style={styles.textoPrincipal}>{producto.nombre}</Text>
          <Text style={styles.textoSecundario}>{producto.categoria}</Text>
        </View>
        <View style={styles.itemProducto3}>
          <Text style={styles.textoTerciario}>{producto.precioVenta}</Text>
        </View>
        <View style={styles.itemBotones}>
          <Button title="❌" onPress={() => {
            indiceSeleccionado = indice;
            setModalVisible(true);
          }} />
        </View>
      </TouchableOpacity>
    );
  };

  return (
    <View style={styles.container}>
      <ScrollView>
        <View>
          <View style={styles.ViewTitulo}>
            <Text style={styles.titulo}>PRODUCTOS</Text>
          </View>
          <TextInput
            style={styles.txt}
            value={id}
            placeholder='CODIGO'
            onChangeText={id => { setId(id) }}
            keyboardType='numeric'
            editable={esNuevo}
          />
          <TextInput
            style={styles.txt}
            value={nombre}
            placeholder='NOMBRE'
            onChangeText={nombre => { setNombre(nombre) }}
          />
          <TextInput
            style={styles.txt}
            value={categoria}
            placeholder='CATEGORIA'
            onChangeText={categoria => { setCategoria(categoria) }}
          />
          <TextInput
            style={styles.txt}
            value={precioCompra}
            placeholder='PRECIO DE COMPRA'
            onChangeText={precioCompra => {
              setPrecioCompra(precioCompra);
              calcularPrecioVenta();
            }}
            keyboardType='numeric'
          />
          <TextInput
            style={styles.txt}
            value={precioVenta}
            placeholder='PRECIO DE VENTA'
            onChangeText={txt => { setPrecioVenta(txt) }}
            keyboardType='numeric'
            editable={false}
          />

          <View style={styles.areaBotones}>
            <Button
              title='Guardar'
              onPress={() => { guardarProductos() }}
            />
            <Button
              title='Nuevo'
              onPress={() => { limpiar() }}
            />
            <Text style={styles.text}>Elementos: {numElementos}</Text>
          </View>
        </View>
      </ScrollView>
      <View style={styles.areaContenido}>
        <FlatList
          data={productos}
          renderItem={({ index, item }) => {
            return <ItemProducto
              indice={index}
              producto={item}
            />
          }}
          keyExtractor={item => item.id.toString()}
        />
      </View>
      <View style={styles.areaPie}>
        <Text>Sebastián Chamorro</Text>
      </View>

      <Modal
        transparent={true}
        visible={modalVisible}
        animationType="slide"
      >
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Text>Está seguro que quiere eliminar?</Text>
            <View style={styles.modalButtons}>
              <Button title="Aceptar" onPress={eliminarProducto} />
              <Button title="Cancelar" onPress={() => setModalVisible(false)} />
            </View>
          </View>
        </View>
      </Modal>

      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'stretch',
    justifyContent: 'flex-start',
    paddingTop: 50,
    paddingHorizontal: 15
  },
  titulo: {
    fontSize: 20,
    fontWeight: 'bold',
    margin: 5,
    textDecorationLine: 'underline'
  },
  ViewTitulo: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  textoPrincipal: {
    fontSize: 18,
  },
  textoSecundario: {
    fontSize: 14,
    fontWeight: 'bold'
  },
  textoTerciario: {
    fontSize: 15,
    fontWeight: 'bold'
  },
  txt: {
    borderWidth: 2,
    borderColor: "#cfcfcf",
    paddingTop: 3,
    paddingHorizontal: 5,
    marginBottom: 5,
    borderRadius: 8
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
    paddingVertical: 5,
    fontWeight: 'bold'
  },
  areaContenido: {
    flex: 6,
    //backgroundColor: "lightblue",
  },
  itemProducto: {
    backgroundColor: "#ecfcd0",
    marginVertical: 5,
    padding: 5,
    flexDirection: 'row',
    borderWidth: 1,
    borderRadius: 10
  },
  itemProducto1: {
    flex: 2,
    justifyContent: 'center',
    alignItems: 'center',
    //backgroundColor: "#ecfcd0",
    paddingHorizontal: 5,
  },
  itemProducto2: {
    flex: 4,
    //backgroundColor: "#ecfcd0",
    paddingHorizontal: 5,
  },
  itemBotones: {
    //backgroundColor: "#ecfcd0",
    flex: 4,
    flexDirection: 'row',
    alignItems: "center",
    justifyContent: "space-evenly",
  },
  itemProducto3: {
    flex: 2,
    // backgroundColor: "#ecfcd0",
    alignItems: 'flex-end',
    justifyContent: 'center',
    paddingHorizontal: 5,
  },
  areaPie: {
    flex: 1,
    //backgroundColor: "lightpink",
    justifyContent: 'center',
    alignItems: 'flex-end'
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  modalContent: {
    width: 300,
    padding: 20,
    backgroundColor: 'white',
    borderRadius: 10,
    alignItems: 'center',
  },
  modalButtons: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '100%',
    marginTop: 20,
  },
});