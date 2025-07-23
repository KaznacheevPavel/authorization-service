import {fetchCsrfToken} from "@/src/lib/csrf";
import {CsrfResponse} from "#types/csrfResponse";

export async function login(username: string, password: string) {
    const csrf: CsrfResponse = await fetchCsrfToken();

    const url = "http://localhost:8081/login";

    const headers = new Headers();
    headers.set("Content-Type", "application/x-www-form-urlencoded");
    headers.set(csrf.headerName, csrf.token);

    const response = await fetch(url, {
        method: "POST",
        headers: headers,
        credentials: "include",
        body: new URLSearchParams({
            username: username,
            password: password,
        })
    });

    if (response.ok) {
        return response.headers.get("Location");
    } else {
        throw Error("Ошибка авторизации");
    }

}