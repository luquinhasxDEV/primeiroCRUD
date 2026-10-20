import { useState } from 'react';
import api from './api';
import './App.css';

function App() {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({ name: '', email: '', password: '' });
  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');

    if (isLogin) {
      // Lógica de Login: consulta os utilizadores na API
      try {
        const response = await api.get('/users');
        const user = response.data.find(
          (u) => u.email === formData.email && u.password === formData.password
        );

        if (user) {
          setMessage(`Bem-vindo(a), ${user.name}! Login efetuado com sucesso.`);
        } else {
          setMessage('Erro: E-mail ou palavra-passe incorretos.');
        }
      } catch (err) {
        setMessage('Erro ao ligar ao servidor backend.');
      }
    } else {
      // Lógica de Registo: envia os dados para POST /api/users/register
      try {
        const response = await api.post('/users/register', {
          name: formData.name,
          email: formData.email,
          password: formData.password,
        });
        setMessage(`Utilizador ${response.data.name} registado com sucesso! Faça o login.`);
        setIsLogin(true);
      } catch (err) {
        if (err.response && err.response.data) {
          setMessage(`Erro: ${err.response.data}`);
        } else {
          setMessage('Erro ao registar utilizador.');
        }
      }
    }
  };

  return (
    <div className="container">
      <div className="card">
        <h2>{isLogin ? 'Iniciar Sessão' : 'Criar Conta'}</h2>

        {message && <div className="alert">{message}</div>}

        <form onSubmit={handleSubmit}>
          {!isLogin && (
            <div className="form-group">
              <label>Nome</label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
              />
            </div>
          )}

          <div className="form-group">
            <label>E-mail</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Palavra-passe</label>
            <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
            />
          </div>

          <button type="submit" className="btn-primary">
            {isLogin ? 'Entrar' : 'Cadastrar'}
          </button>
        </form>

        <p className="toggle-text">
          {isLogin ? 'Ainda não tem conta?' : 'Já tem uma conta?'}
          <span onClick={() => { setIsLogin(!isLogin); setMessage(''); }}>
            {isLogin ? ' Cadastre-se' : ' Inicie Sessão'}
          </span>
        </p>
      </div>
    </div>
  );
}

export default App;