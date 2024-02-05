init_arr([], _) :- !.

init_arr([(K, V) | T], R) :-
    arr(R, _),
    retract(arr(R, _)),
    put_index((K, V), T, R), !.

init_arr([(K, V) | T], R) :-
    put_index((K, V), T, R), !.

put_index((K, V), T, R) :-
    assert(arr(R, (K, V))),
    R1 is R + 1,
    init_arr(T, R1), !.

map_build([], _) :- !.

map_build(LIST_MAP, TREE_MAP) :-
    init_arr(LIST_MAP, 0),
    length(LIST_MAP, L),
    L1 is L - 1,
    map_build(0, L1, TREE_MAP), !.

map_build(LEFT, LEFT, node(V, 0, 0)) :-
    arr(LEFT, V), !.

map_build(LEFT, RIGHT, node(V, 0, RR)) :-
    1 is RIGHT - LEFT,
    map_build(RIGHT, RIGHT, RR),
    arr(LEFT, V), !.

map_build(LEFT, RIGHT, node(V, RL, RR)) :-
    MID is div((LEFT + RIGHT), 2),
    LEFT1 is MID - 1,
    RIGHT1 is MID + 1,
    map_build(LEFT, LEFT1, RL),
    map_build(RIGHT1, RIGHT, RR),
    arr(MID, V), !.

map_get(node((K, V), _, _), K, V) :- !.

map_get(node((CURK, _), RL, _), K, V) :-
    K < CURK, !,
    map_get(RL, K, V).

map_get(node((CURK, _), _, RR), K, V) :-
    K > CURK, !,
    map_get(RR, K, V).

map_values(0, []) :- !.

map_values([], []) :- !.

map_values(node((_, V), 0, 0), [V]) :- !.

map_values(node((_, V), RL, RR), R) :-
    map_values(RL, L1),
    append(L1, [V], RN),
    map_values(RR, R1),
    append(RN, R1, R), !.

map_keys(0,[]) :- !.

map_keys([], []) :- !.

map_keys(node((K, _), 0, 0), [K]) :- !.

%map_keys(node((K, _), RL, RR), R) :-
%    map_keys(RL, L1),
%    append(L1, [K], RN),
%    map_keys(RR, R1),
%    append(RN, R1, R), !.

map_keys(node((K, _), RL, RR), R) :-
    map_keys(RL, L1),
    concatenate(L1, [K], RN),
    map_keys(RR, R1),
    concatenate(RN, R1, R), !.

concatenate([], L, L) :- !.
concatenate([H | T], L, [H | R]) :- concatenate(T, L, R), !.

%map_keys(node((K, _), RL, RR), R) :-
%    map_keys(RR, R1),
%    RN = [K | R1],
%    map_keys(RL, L1),
%    R = [L1 | RN], !.

