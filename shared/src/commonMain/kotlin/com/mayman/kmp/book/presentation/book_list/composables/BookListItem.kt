package com.mayman.kmp.book.presentation.book_list.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.mayman.kmp.book.domain.model.Book
import kmp.shared.generated.resources.Res
import kmp.shared.generated.resources.broken_image
import org.jetbrains.compose.resources.painterResource
import kotlin.math.round

//
// Created by Mahmoud Ayman Mostafa on 26/09/2026.
//

@Composable
fun BookListItem(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.clickable(onClick = onClick).clip(
            RoundedCornerShape(16.dp)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.height(100.dp),
                contentAlignment = Alignment.Center
            )
            {
                var imageResult by remember { mutableStateOf<Result<Painter>?>(null) }
                val painter = rememberAsyncImagePainter(model = book.imageUrl, onSuccess = {
                    imageResult =
                        if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                            Result.success(it.painter)
                        } else {
                            Result.failure(Exception("not valid image"))
                        }
                }, onError = {
                    it.result.throwable.printStackTrace()
                    imageResult = Result.failure(it.result.throwable)
                })

                when (val result = imageResult) {
                    null -> CircularProgressIndicator()
                    else -> {
                        Image(
                            painter = if (result.isSuccess) painter else painterResource(Res.drawable.broken_image),
                            contentDescription = null,
                            contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit,
                            modifier = Modifier.aspectRatio(
                                0.65f,
                                matchHeightConstraintsFirst = true
                            )
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f).padding(start = 8.dp),
                verticalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = book.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (book.author.isNotBlank())
                    Text(
                        text = book.author,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.StarRate,
                        tint = Color(0xFFFFDD02),
                        contentDescription = null
                    )
                    Text(
                        text = "${round(book.rating * 10) / 10.0}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowRight,
                contentDescription = null,
                modifier = Modifier.size(46.dp)
            )
        }
    }
}

@Preview
@Composable
private fun BookListItemPreview() {
    MaterialTheme {
        BookListItem(
            book = Book(
                id = "nec",
                name = "Bette Shaw",
                author = "brute",
                description = "sed",
                imageUrl = "https://picsum.photos/200",
                rating = 4.68576453
            ),
            onClick = { },
        )
    }
}