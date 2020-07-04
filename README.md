# enocLinkTest

## Installation:

Clone this repository and import into Android Studio
```bash
git  clone git@github.com:rehabMohamed/enocLinkTest.git
```

## Architecture:
The application follows the MVP architecture. It consists of 3 screens. Each screen exists in a separate package under the main package as follows:
- **Splash screen:** An entry of the application that takes the user to either the login screen or the profile screen(if the user already logged it)
- **Login Screen:** Takes an email and password, validates them and login the user if the creadentials are correct
- **Profile screen:** Displays the user email, password and profile photo. The user can change his/her profile photo by clicking it to open the gallary or the camira. After choosing a photo, the app adds a grayscale filter then displays the photo. If the user didn't change his/her profile photo, the app searches if the user has a gravatar account and displays the gravatar photo.

## Network:
The app uses [Retrofit2](https://square.github.io/retrofit/) for backend communication. It has 3 APIs
- POST /sessions/new { “email”: “:email”, “password”: “:password”} -> {
“userid”: “:userid”, “token”: “:token” }
- GET /users/:userid -> { “email”: “:email”, “avatar_url”: “:avatar_url” }
- POST /users/:userid/avatar { “avatar”: “:base64_encoded_data” } -> {
“avatar_url”: “:avatar_url” }

## Dependency injection
[dagger 2](https://github.com/google/dagger) is used to provide the dependencies to the app components.
- **AppModule:** provides the global app dependencies such as **UserRepository**, **UserManager**, **Retrofit** ...
- **SplashModule:** provides the presenter to the SplashActivity
- **LoginModule:** provides the presenter to LoginActivity
- **ProfileModule:** provides the presenter to ProfileActivity

## Image loading:
[Glide](https://github.com/bumptech/glide) is used to load the images as it is recommended by google for its memory efficiency. Extension functions are created under the utils package to separate the Glide dependency from the screens’ implementation

## Threading:
[Coroutines](https://github.com/Kotlin/kotlinx.coroutines) is used for network communication in the background.

## Backend:
As there is no live backend provided, MockInterceptor is used to provide json responses to the app.
- All requests return success http code.
- GET /users/:userid -> always return null avatar_url to test the Gravatar feature.
- POST /users/:userid/avatar -> always return a fixed url for a dummy photo.

## Testing
Used Junit4 and Mockito for unit testing / behaviour testing.
- **MD5UtilKtTest:** tests the email hash conversion for Gravatar
- **ValidationsKtTest:** tests the login validations.
- **UserRepositoryImpTest:** tests the actions after communicating with the backend.

