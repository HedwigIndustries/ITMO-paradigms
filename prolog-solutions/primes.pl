init(MAX_N):- eratosthene(2, MAX_N).
composite_table(J, STEP, MAX_N):-
    J < MAX_N + 1,
    assert(composite(J)),
    J1 is J + STEP,
    composite_table(J1, STEP, MAX_N).
eratosthene(I, MAX_N):-
    \+ composite(I),
    J is I * I,
    composite_table(J, I, MAX_N).
eratosthene(I, MAX_N):- I < MAX_N + 1, I1 is I + 1, eratosthene(I1, MAX_N).
prime(N):- N > 1, \+ composite(N), !.

first_divisor(N, I, N) :- I * I > N, !.
first_divisor(N, I, DIV):-
    0 is N mod I,
    DIV is I, !.
first_divisor(N, I, DIV):-
    \+ 0 is N mod I,
    I1 is I + 1,
    first_divisor(N, I1, DIV), !.

prime_divisors(1, []):- !.

prime_divisors(N, R):- number(N),
    first_divisor(N, 2, DIV),
    N1 is div(N, DIV),
    prime_divisors(N1, R1),
    append([DIV], R1, R), !.

calc(1,[], _):- !.
calc(N, [DIV | T], DIV_MAX):-
    DIV_MAX =< DIV,
    calc(N1, T, DIV),
    N is N1 * DIV.

prime_divisors(N, [DIV | T]):-
    number(DIV),
    calc(N1, T, DIV),
    N is N1 * DIV, !.

% mode square
square_divisors(N,D):-
    number(N),
    N1 is N * N,
    prime_divisors(N1, D), !.

square_divisors(1, []):- !.

square_divisors(N, R):-
    prime_divisors(N1, R),
    N is N1 * N1, !.
% mode square

% mode cube
cube_divisors(N,D):-
    number(N),
    N1 is N * N * N,
    prime_divisors(N1, D), !.

cube_divisors(1, []):- !.

cube_divisors(N, R):-
    prime_divisors(N1, R),
    N is N1 * N1 * N1, !.
% mode cube

% mode compact
compact(P, CUR_DIV, [], R1):- !.

compact(P, CUR_DIV, [DIV | T], R1):-
    CUR_DIV =:= DIV,
    P1 is P + 1,
    compact(P1, DIV, T, R1), !.

compact(P, CUR_DIV, [DIV | T], [(CUR_DIV, P)| R1]):-
    \+ CUR_DIV =:= DIV,
    compact(1, DIV, T, R1), !.

compact_prime_divisors(1, []):- !.

compact_prime_divisors(N, R):-
    number(N),
    prime_divisors(N, R1),
    compact(0, 2, R1, R).
% mode compact

%
%divisors_divisors(N, DDs) :- number(N), get_divisors(1, N, DDs).
%
%get_divisors(N, N, [D]) :- prime_divisors(N, D), !.
%
%
%get_divisors(K, N, [D | DDs]) :-
%  K < N, 0 is mod(N, K),
%  prime_divisors(K, D),
%  K1 is K + 1,
%  get_divisors(K1, N, DDs), !.
%
%get_divisors(K, N, DDs) :-
%  K < N, \+ 0 is mod(N, K),
%  K1 is K + 1, get_divisors(K1, N, DDs).
%
%
%divisors_divisors(N, [D]) :- var(N), !, prime_divisors(N, D).
%divisors_divisors(N, [_ | T]) :- var(N), !, divisors_divisors(N, T).
%
