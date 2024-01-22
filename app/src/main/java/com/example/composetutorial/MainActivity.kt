package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ComposeTutorialTheme {
                Conversation(SampleData.conversationSample)
            }
        }
    }
}

data class Message(val author: String, val body: String)



@Composable
fun MessageCard(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)){
        Image(
            painter = painterResource(R.drawable.bird),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)//Set image size to 40dp
                .clip(CircleShape)// Clip image to be shaped as a circle
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
    // Add a horizontal space between the image and the column
    Spacer(modifier = Modifier.width(8.dp))

    // We keep track if the message is expanded or not in this
    // variable
    var isExpanded by remember {mutableStateOf(false)}
    // surfaceColor will be updated gradually from one color to the other
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
    )

    // We toggle the isExpanded variable when we click on this Column
    Column(modifier = Modifier.clickable {isExpanded = !isExpanded}) {
        Text(
            text = msg.author,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelLarge
        )
        // Add a vertical space between the author and message
        Spacer(modifier = Modifier.height(4.dp))

        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 1.dp,
            // surfaceColor color will be changing gradually from primary to surface
            color = surfaceColor,
            modifier = Modifier.animateContentSize().padding(1.dp)
        ) {
            Text(
                text = msg.body,
                modifier = Modifier.padding(all=4.dp),
                // If the message is expanded, we display all its content
                // otherwise we only display the first line
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
}
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Surface {
            MessageCard(
                msg = Message("Patrick", "Hey, take a look at Jetpack Compose, it's great!"))
        }
    }

}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn{
        items(messages) {message ->
            MessageCard(msg = message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}

/**
 * SampleData for Jetpack Compose Tutorial
 */
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Patrick",
            "Test...Test...Test..."
        ),
        Message(
            "Patrick",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "Patrick",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Patrick",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Patrick",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "Patrick",
            "It's available from API 21+ :)"
        ),
        Message(
            "Patrick",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Patrick",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Patrick",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Patrick",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Patrick",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Patrick",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Patrick",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}