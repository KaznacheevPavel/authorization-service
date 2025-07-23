import type { CsrfResponse } from "@/types/csrfResponse";

export async function fetchCsrfToken(): Promise<CsrfResponse> {
    const url = "http://localhost:8081/csr";

    const response = await fetch(url, {
        method: "GET",
        credentials: "include"
    });

    if (!response.ok) {
        throw new Error("Ошибка получения CSRF токена")
    }

    return response.json();
}