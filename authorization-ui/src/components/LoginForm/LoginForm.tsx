'use client';

import {FormEvent, useState} from 'react';
import { useRouter } from 'next/navigation';
import { login } from '@/src/lib/login';
import styles from '@/src/components/LoginForm/LoginForm.module.css'

export default function LoginForm() {

    const router = useRouter();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        setIsLoading(true);
        setError('');
        try {
            const redirectLocation = await login(username, password);
            router.push(redirectLocation);
        } catch (error) {
            setError("Ошибка авторизации");
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className={styles.formContainer}>
            <h1 className={styles.title}>Вход</h1>
            {error && <span className={styles.errorMessage}>{error}</span>}
            <form onSubmit={handleSubmit} className={styles.form}>
                <div className={styles.formGroup}>
                    <label htmlFor="username" className={styles.label}>Имя пользователя</label>
                    <input
                        type="text"
                        id="username"
                        className={styles.input}
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className={styles.formGroup}>
                    <label htmlFor="password" className={styles.label}>Пароль</label>
                    <input
                        type="password"
                        id="password"
                        className={styles.input}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button
                    type="submit"
                    className={`${styles.button} ${isLoading ? styles.loading : ''}`}
                    disabled={isLoading}
                >{isLoading ? '' : 'Войти'}</button>
                <a href="http://localhost:8081/oauth2/authorization/yandex">Вход через Яндекс</a>
            </form>
        </div>
    );
}