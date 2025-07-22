import '@/src/styles/reset.css';
import { roboto } from '@/src/styles/font';

export default function RootLayout({
    children,
}: {
    children: React.ReactNode;
}) {
    return (
        <html lang="ru" className={roboto.className}>
            <body>{children}</body>
        </html>
    );
}