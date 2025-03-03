import { Button } from '@/components/button';
import { ArrowRightCircle } from 'lucide-react';

export default function Home() {
  return (
    <main>
      <div>Hello world!</div>

      <Button>
        Enviar
        <ArrowRightCircle />
      </Button>
    </main>
  );
}
