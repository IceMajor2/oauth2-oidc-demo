# oauth2-oidc-demo
**OAuth 2.0** is a widely-used **authorization** protocol. It enables an application (**resource server**) to secure its API
and delegate users elsewhere (to an **authorization server**) where they log in and return back with the access tokens,
which are then used to retrieve the resources. Oftentimes, in order to automate and simplify the whole process, the
**OAuth clients** are configured which act on behalf of a user.

As an **authentication** extension to the OAuth 2.0, there is the **OpenID Connect** (OIDC) protocol.

This repository consists of a demo project that sets up all of the mentioned above in Spring Boot. It was created for academic purposes
and does not follow all the aspects of security best practices. For example, the private and public keys are exposed and that should
not happen in production.

You'll find here 3 services that are standalone Spring Boot applications (run on different ports).
* **auth-server**: authorization server that persists the data such as
    * users and authorities;
    * OAuth clients;
    * sessions;
    * authorizations & consents given by users to the clients.
* **resource-server**: a REST API with one endpoint (`/welcome`) which requires a user to have a `user.read` scope in order to access it.
* **auth-client**: configuration of OAuth 2.0 client; includes a gateway that reroutes requests to the resource & authorization servers.
## Setup
1. Install PostgreSQL and make sure it is set up correctly in the `auth-server`'s `application.yml` file.
2. Clone this repository.

    `git clone https://github.com/IceMajor2/oauth2-oidc-demo.git`

3. Run each service. **NOTE:** authorization server must be launched before the client service.
   
     * In each of the directories (`auth-server`, `resource-server` & `auth-client`) run:
  
       `./mvnw clean compile exec:java`.

## Test out
* Access a resource server's endpoint through client on `127.0.0.1:8082/welcome`.
* Log in as `admin` with password `admin`.
* Authorize the client by granting the consent.
* You should see a JSON response from resource server's endpoint:
```
{
    "message": "Welcome, admin!"
}
```
