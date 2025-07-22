import LoginForm from '@/src/components/LoginForm/LoginForm';
import styles from '@/src/app/auth/login/page.module.css';

export default function Page() {
    return (
        <div className={styles.container}>
            <LoginForm />
        </div>
    )
}