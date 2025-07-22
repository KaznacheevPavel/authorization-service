export async function login(username: string, password: string) {
    const url = "http://localhost:8081/login";

    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        credentials: "include",
        body: new URLSearchParams({
            username: username,
            password: password
        })
    });

    if (response.ok) {
        return response.headers.get("Location");
    } else {
        throw Error("Ошибка авторизации");
    }

}