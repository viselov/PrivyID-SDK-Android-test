# PrivySDK Sign Embeded

add PrivySDKEmbeded dependencies to your project

```kotlin
implementation 'id.privy.sdk.sign:sign-embeded:0.0.1'
```

you must have an document token, to view the document. in here, has two options to view document

- New Tab Document Viewer
- PrivyDocumentViewer

## Basic Parameter

add permission to Internet access on your Android Project

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

we deliver more configuration to view and make easy signing process on user expirence, so you must define first several parameter

| Properties           | Type    | Status   | Default |
| -------------------- | ------- | -------- | ------- |
| debug                | Boolean | required | true    |
| X Coordinate         | Float   | optional | -       |
| Y Coordinate         | Float   | optional | -       |
| page                 | Integer | optional | -       |
| fixed                | Boolean | required | false   |
| visibility signature | Boolean | required | true    |
| privyID              | String  | optional | -       |

you can use **Utils Class** to create collecting query

```kotlin
PrivySignUtils.paramsRequest(
    privyID = "RA3150",
    visibility = true,
    page = 2,
    coordinateX = 300f,
    coordinateY = 200f
)
```

## User Guide (New Tab Document Viewer)

its easy, you just add simple line of codes

```kotlin
PrivySDK(context = this)
    .openDocument(
        tokenDocument = YOUR_DOCUMENT_TOKEN,
        queryDocument = PrivySignUtils.paramsRequest(
            privyID = "RA3150",
            visibility = true,
            page = 2,
            coordinateX = 300f,
            coordinateY = 200f
        )
    )
```

## User Guide (PrivyDocumentViewer)

its more easy to custom component, look line of codes bellow

add, this code to your XML layout

```xml
<id.privy.android.sign.PrivySignViewer
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/viewer">
</id.privy.android.sign.PrivySignViewer>
```

assign the layout to define in your class

```kotlin
val viewer = findViewById(R.id.viewer)
viewer.openDocument(tokenDocument = documentToken,
            queryDocument = PrivySignUtils.paramsRequest(
            privyID = "RA3150",
            visibility = true,
            page = 2,
            coordinateX = 300f,
            coordinateY = 200f
        ))
```

## Get callback Action

you can implementation callback when:

- afterSign()

- afterReview()

- afterAction()


just implement, **PrivySignCallback**

```kotlin
viewer.setCallbackSign(object : PrivySignUtils.PrivySignCallback{
    override fun afterAction() {
        // what do you want after action?
    }

    override fun afterSign() {
        // what do you want after sign?
    }

    override fun afterReview() {
        // what do you want after review?
    }

})
```