import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import { PinchZoomImage } from 'react-native-pinch-zoom-image';

export default function App() {
  return (
    <View style={styles.container}>
      <PinchZoomImage
        source={{ uri: 'https://picsum.photos/seed/picsum/200/300' }}
        style={styles.image}
        maximumZoomScale={2}
        minimumZoomScale={1}
        onLoad={(event: any) =>
          console.log('onLoad', JSON.stringify(event?.nativeEvent))
        }
        onLoadStart={(event: any) =>
          console.log('onLoadStart', JSON.stringify(event?.nativeEvent))
        }
        onLoadEnd={(event: any) =>
          console.log('onLoadEnd', JSON.stringify(event?.nativeEvent))
        }
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  image: {
    width: '100%',
    height: '100%',
  },
});
