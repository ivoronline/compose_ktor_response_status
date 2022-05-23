package com.ivoronline.compose_ktor_response_status

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch

//==================================================================
// MAIN ACTIVITY
//==================================================================
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {

      var status by remember { mutableStateOf("") }
      val coroutineScope = rememberCoroutineScope()

      Button(onClick = { coroutineScope.launch { status = callURL() } }) {
        Text("RESPONSE: $status")
      }

    }
  }
}

//==================================================================
// CALL URL
//==================================================================
suspend fun callURL() : String {

  //CONFIGURE CLIENT
  val client = HttpClient(CIO)

  //CAL URL
  val response: HttpResponse = client.get("http://192.168.0.102:8080/SendRequest")

  //CLOSE CLIENT
  client.close()

  //RETURN SOMETHING
  return response.status.toString();    //status=200 OK, value=200, description=OK

}

